package com.example.recipy.database

import com.example.recipy.data.MealsRepository
import com.example.recipy.model.Meal
import com.example.recipy.model.MealDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class OfflineMealsRepository(
    private val shoppingDao: MealDetailsDao,
    private val network: MealsRepository
) {
    fun getFavouritesStream(): Flow<List<Meal>> = shoppingDao.getShoppingMeals().map {list -> list.filter {it.isFavourite == true}.map { it.toMeal() }}

    fun getFavouriteItemStream(id: String): Flow<Meal?> = shoppingDao.getShoppingMealWithId(id).filter {it?.isFavourite == true}.map { it?.toMeal() }

    fun getCartStream(): Flow<List<MealDetails>> = shoppingDao.getShoppingMeals().map {list -> list.filter { it.isInCart == true }}

    fun getCartItemStream(id: String): Flow<MealDetails?> = shoppingDao.getShoppingMealWithId(id).filter { it?.isInCart == true}

    suspend fun addToFavourite(meal: Meal) {
        val mealDetails = shoppingDao.getShoppingMealWithId(meal.id).first()
        if (mealDetails != null) {
            mealDetails.isFavourite = true
            shoppingDao.upsertShoppingMeal(mealDetails)
            return
        }
        val mealDetails2 = network.getMealWithId(meal.id)
        if (mealDetails2 != null) {
            mealDetails2.isFavourite = true
            shoppingDao.upsertShoppingMeal(mealDetails2)
        }
    }

    suspend fun removeFromFavourites(meal: Meal) {
        val mealDetails = shoppingDao.getShoppingMealWithId(meal.id).first()
        if (mealDetails != null) {
            mealDetails.isFavourite = false
            shoppingDao.upsertShoppingMeal(mealDetails)
        }
    }

    suspend fun addToCart(mealDetails: MealDetails) {
        mealDetails.isInCart = true
        shoppingDao.upsertShoppingMeal(mealDetails)
    }

    suspend fun removeFromCart(mealDetails: MealDetails) {
        mealDetails.isInCart = false
        shoppingDao.upsertShoppingMeal(mealDetails)
    }

    suspend fun removeFromCart(mealId: String) {
        val meal = getCartItemStream(mealId).first()
        if (meal != null)
            shoppingDao.deleteShoppingMeal(meal)
    }

}