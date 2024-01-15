package com.example.recipy

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.recipy.database.AppDatabase
import com.example.recipy.database.MealDetailsDao
import com.example.recipy.model.MealDetails
import org.junit.Before
import org.junit.runner.RunWith
import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class MealDetailsDaoTest {
    private lateinit var mealDetailsDao: MealDetailsDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun createDb(){
        val context: Context = ApplicationProvider.getApplicationContext()

        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        mealDetailsDao = appDatabase.shoppingDao()
    }

    @After
    fun closeDb(){
        appDatabase.close()
    }

    @Test
    fun daoUpsert_insertsProductToDb() = runBlocking {
        upsertMealToDb(mealDetails1)
        val mealDetails = mealDetailsDao.getMealWithId(mealDetails1.id).first()
        assertEquals(mealDetails, mealDetails1)
    }

    @Test
    fun daoUpsert_updatesExistingProductInDb() = runBlocking {
        upsertMealToDb(mealDetails1)

        val beforeUpdate = mealDetailsDao.getMealWithId(mealDetails1.id).first()
        assertEquals(beforeUpdate?.isFavourite, false)

        mealDetails1.isFavourite = true
        upsertMealToDb(mealDetails1)
        val afterUpdate = mealDetailsDao.getMealWithId(mealDetails1.id).first()
        assertEquals(afterUpdate?.isFavourite, true)
    }

    fun daoDelete_deletesProductInDb() = runBlocking {
        upsertMealToDb(mealDetails1)

        mealDetailsDao.deleteShoppingMeal(mealDetails1)
        val mealDetails = mealDetailsDao.getMealWithId(mealDetails1.id).first()
        assertEquals(mealDetails, null)
    }

    @Test
    fun daoGetShoppingMeals_returnsEmptyListWhenNoMealInCart() = runBlocking {
        upsertMealToDb(mealDetails1)
        upsertMealToDb(mealDetails2)

        val inCart = mealDetailsDao.getShoppingMeals().first()
        assertTrue(inCart.isEmpty())
    }

    @Test
    fun daoGetShoppingMeals_returnsMealsInCart() = runBlocking {
        mealDetails1.isInCart = true
        upsertMealToDb(mealDetails1)
        upsertMealToDb(mealDetails2)

        val inCart = mealDetailsDao.getShoppingMeals().first()
        assertEquals(inCart.size, 1)
        assertEquals(inCart.first(), mealDetails1)
    }

    @Test
    fun daoGetFavouriteMeals_returnsEmptyListWhenNoMealInFavourites() = runBlocking {
        upsertMealToDb(mealDetails1)
        upsertMealToDb(mealDetails2)

        val inCart = mealDetailsDao.getFavouriteMeals().first()
        assertTrue(inCart.isEmpty())
    }

    @Test
    fun daoGetFavouriteMeals_returnsMealsInFavourites() = runBlocking {
        mealDetails1.isFavourite = true
        upsertMealToDb(mealDetails1)
        upsertMealToDb(mealDetails2)

        val inCart = mealDetailsDao.getFavouriteMeals().first()
        assertEquals(inCart.size, 1)
        assertEquals(inCart.first(), mealDetails1)
    }

    @Test
    fun daoGetShoppingMealWithId_returnsNullWhenNoShoppingMealWithGivenId() = runBlocking {
        upsertMealToDb(mealDetails1)

        val meal2 = mealDetailsDao.getShoppingMealWithId(mealDetails2.id).first()
        assertEquals(meal2, null)
    }

    @Test
    fun daoGetShoppingMealWithId_returnsNullWhenMealIsNotInCart() = runBlocking {
        upsertMealToDb(mealDetails1)

        val meal = mealDetailsDao.getShoppingMealWithId(mealDetails1.id).first()
        assertEquals(meal, null)
    }

    @Test
    fun daoGetShoppingMealWithId_returnsShoppingMeal() = runBlocking {
        upsertMealToDb(mealDetails1)
        mealDetails2.isInCart = true
        upsertMealToDb(mealDetails2)

        val meal = mealDetailsDao.getShoppingMealWithId(mealDetails2.id).first()
        assertEquals(meal, mealDetails2)
    }

    @Test
    fun daoGetFavouriteMealWithId_returnsNullWhenNoFavouriteMealWithGivenId() = runBlocking {
        upsertMealToDb(mealDetails1)

        val meal2 = mealDetailsDao.getFavouriteMealWithId(mealDetails2.id).first()
        assertEquals(meal2, null)
    }

    @Test
    fun daoGetFavouriteMealWithId_returnsNullWhenMealIsNotInFavourites() = runBlocking {
        upsertMealToDb(mealDetails1)

        val meal = mealDetailsDao.getFavouriteMealWithId(mealDetails1.id).first()
        assertEquals(meal, null)
    }

    @Test
    fun daoGetFavouriteMealWithId_returnsFavouriteMeal() = runBlocking {
        upsertMealToDb(mealDetails1)
        mealDetails2.isFavourite = true
        upsertMealToDb(mealDetails2)

        val meal = mealDetailsDao.getFavouriteMealWithId(mealDetails2.id).first()
        assertEquals(meal, mealDetails2)
    }

    @Test
    fun daoGetMealWithId_returnsNullWhenNoMealWithGivenId() = runBlocking {
        upsertMealToDb(mealDetails1)
        val mealDetails = mealDetailsDao.getMealWithId(mealDetails2.id).first()
        assertEquals(mealDetails, null)
    }

    @Test
    fun daoGetMealWithId_returnsMealWithGivenId() = runBlocking {
        upsertMealToDb(mealDetails1)
        upsertMealToDb(mealDetails2)

        val mealDetails = mealDetailsDao.getMealWithId(mealDetails2.id).first()
        assertEquals(mealDetails, mealDetails2)
    }

    @Test
    fun getMealsWithSearch_returnsNullWhenNoMealWithGivenName() = runBlocking {
        upsertMealToDb(mealDetails1)

        val meal = mealDetailsDao.getMealsWithSearch("Fake").first()
        assertEquals(meal.size, 0)
    }

    @Test
    fun getMealsWithSearch_returnsMealWithGivenName() = runBlocking {
        upsertMealToDb(mealDetails1)

        val meal = mealDetailsDao.getMealsWithSearch("Chicken Alfredo Primavera").first()
        assertEquals(meal.size, 1)
    }

    private suspend fun upsertMealToDb(meal: MealDetails){
        mealDetailsDao.upsertShoppingMeal(meal)
    }

    private val mealDetails1 = MealDetails(
        id = "52796",
        name = "Chicken Alfredo Primavera",
        drinkAlternate = null,
        category = "Chicken",
        area = "Italian",
        instructions = "instructions1",
        thumbUrl = "https://www.themealdb.com/images/media/meals/syqypv1486981727.jpg",
        tags = "Pasta,Meat,Dairy",
        youtubeUrl = "https://www.youtube.com/watch?v=qCIbq8HywpQ",
        ingredient1 = "Butter",
        ingredient2 = "Olive Oil",
        ingredient3 = "Chicken",
        ingredient4 = "Salt",
        ingredient5 = "Squash",
        ingredient6 = "Broccoli",
        ingredient7 = "mushrooms",
        ingredient8 = "Pepper",
        ingredient9 = "onion",
        ingredient10 = "garlic",
        ingredient11 = "red pepper flakes",
        ingredient12 = "white wine",
        ingredient13 = "milk",
        ingredient14 = "heavy cream",
        ingredient15 = "Parmesan cheese",
        ingredient16 = "bowtie pasta",
        ingredient17 = "Salt",
        ingredient18 = "Pepper",
        ingredient19 = "Parsley",
        ingredient20 = "",
        measure1 ="2 tablespoons",
        measure2 ="3 tablespoons",
        measure3 ="5 boneless",
        measure4 ="1 teaspoon",
        measure5 ="1 cut into 1/2-inch cubes",
        measure6 ="1 Head chopped",
        measure7 ="8-ounce sliced",
        measure8 ="1 red",
        measure9 ="1 chopped",
        measure10 = "3 cloves",
        measure11 = "1/2 teaspoon",
        measure12 = "1/2 cup",
        measure13 = "1/2 cup",
        measure14 = "1/2 cup",
        measure15 = "1 cup grated",
        measure16 = "16 ounces",
        measure17 = "pinch",
        measure18 = "pinch ",
        measure19 = "chopped",
        measure20 = "",
    )
    private val mealDetails2 = MealDetails(
        id = "52772",
        name = "Teriyaki Chicken Casserole",
        drinkAlternate = null,
        category = "Chicken",
        area = "Japanese",
        instructions = "instructions2",
        thumbUrl = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg",
        tags = "Meat,Casserole",
        youtubeUrl = "https://www.youtube.com/watch?v=4aZr5hZXP_s",
        ingredient1 = "soy sauce",
        ingredient2 = "water",
        ingredient3 = "brown sugar",
        ingredient4 = "ground ginger",
        ingredient5 = "minced garlic",
        ingredient6 = "cornstarch",
        ingredient7 = "chicken breasts",
        ingredient8 = "stir-fry vegetables",
        ingredient9 = "brown rice",
        ingredient10 = "",
        ingredient11 = "",
        ingredient12 = "",
        ingredient13 = "",
        ingredient14 = "",
        ingredient15 = "",
        ingredient16 = "",
        ingredient17 = "",
        ingredient18 = "",
        ingredient19 = "",
        ingredient20 = "",
        measure1 = "3/4 cup",
        measure2 = "1/2 cup",
        measure3 = "1/4 cup",
        measure4 = "1/2 teaspoon",
        measure5 = "1/2 teaspoon",
        measure6 = "4 Tablespoons",
        measure7 = "2",
        measure8 = "1 (12 oz.)",
        measure9 = "3 cups",
        measure10 = "",
        measure11 = "",
        measure12 = "",
        measure13 = "",
        measure14 = "",
        measure15 = "",
        measure16 = "",
        measure17 = "",
        measure18 = "",
        measure19 = "",
        measure20 = "",
    )
}