package com.example.recipy.database

import com.example.recipy.model.Meal
import com.example.recipy.model.MealDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class OfflineMealsRepository(
    private val shoppingDao: MealDetailsDao
) {

    fun getItemStream(id: String): Flow<MealDetails?> = shoppingDao.getMealWithId(id)

    fun getFavouritesStream(): Flow<List<Meal>> =
        shoppingDao.getFavouriteMeals().map { list -> list.map { it.toMeal() } }

    fun getFavouriteItemStream(id: String): Flow<Meal?> =
        shoppingDao.getFavouriteMealWithId(id).map { it?.toMeal() }

    fun getFavouriteStreamAsMealDetails(): Flow<List<MealDetails>> = shoppingDao.getFavouriteMeals()

    fun getFavouriteItemStreamAsMealDetails(id: String): Flow<MealDetails?> =
        shoppingDao.getFavouriteMealWithId(id)

    fun getCartStream(): Flow<List<MealDetails>> = shoppingDao.getShoppingMeals()

    fun getCartItemStream(id: String): Flow<MealDetails?> = shoppingDao.getShoppingMealWithId(id)

    suspend fun addToFavourite(mealDetails: MealDetails) {
        val toInsert = shoppingDao.getMealWithId(mealDetails.id).first()
        if (toInsert != null) {
            toInsert.isFavourite = true
            shoppingDao.upsertShoppingMeal(toInsert)
        } else {
            mealDetails.isFavourite = true
            shoppingDao.upsertShoppingMeal(mealDetails)
        }
    }

    suspend fun removeFromFavourites(id: String) {
        val mealDetails = shoppingDao.getMealWithId(id).first()
        if (mealDetails != null) {
            if (mealDetails.isInCart == true) {
                mealDetails.isFavourite = false
                shoppingDao.upsertShoppingMeal(mealDetails)
            } else {
                shoppingDao.deleteShoppingMeal(mealDetails)
            }

        }
    }

    suspend fun addToCart(mealDetails: MealDetails) {
        shoppingDao.upsertShoppingMeal(
            shoppingDao.getMealWithId(mealDetails.id).map {
                if (it != null) {
                    it.isInCart = true
                    it
                } else {
                    mealDetails.isInCart = true
                    mealDetails
                }
            }.first()
        )
    }

    suspend fun removeFromCart(mealDetails: MealDetails) {
        removeFromCart(mealDetails.id)
    }

    suspend fun removeFromCart(mealId: String) {
        val mealDetails = shoppingDao.getMealWithId(mealId).first()
        if (mealDetails != null) {
            if (mealDetails.isFavourite == true) {
                mealDetails.isInCart = false
                mealDetails.marked1 = false
                mealDetails.marked2 = false
                mealDetails.marked3 = false
                mealDetails.marked4 = false
                mealDetails.marked5 = false
                mealDetails.marked6 = false
                mealDetails.marked7 = false
                mealDetails.marked8 = false
                mealDetails.marked9 = false
                mealDetails.marked10 = false
                mealDetails.marked11 = false
                mealDetails.marked12 = false
                mealDetails.marked13 = false
                mealDetails.marked14 = false
                mealDetails.marked15 = false
                mealDetails.marked16 = false
                mealDetails.marked17 = false
                mealDetails.marked18 = false
                mealDetails.marked19 = false
                mealDetails.marked20 = false
                shoppingDao.upsertShoppingMeal(mealDetails)
            } else {
                shoppingDao.deleteShoppingMeal(mealDetails)
            }

        }
    }

    suspend fun updateMeal(mealDetails: MealDetails) = shoppingDao.upsertShoppingMeal(mealDetails)
}