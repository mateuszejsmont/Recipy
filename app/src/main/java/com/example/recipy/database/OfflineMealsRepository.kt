package com.example.recipy.database

import com.example.recipy.data.MealsRepository
import com.example.recipy.model.Meal
import com.example.recipy.model.MealDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class OfflineMealsRepository(
    private val shoppingDao: MealDetailsDao
) {

    fun getItemStream(id: String): Flow<MealDetails?> = shoppingDao.getMealWithId(id)

    fun getFavouritesStream(): Flow<List<Meal>> = shoppingDao.getFavouriteMeals().map {list -> list.map { it.toMeal() }}

    fun getFavouriteItemStream(id: String): Flow<Meal?> = shoppingDao.getFavouriteMealWithId(id).map { it?.toMeal() }

    fun getFavouriteStreamAsMealDetails() : Flow<List<MealDetails>> = shoppingDao.getFavouriteMeals()

    fun getFavouriteItemStreamAsMealDetails(id: String): Flow<MealDetails?> = shoppingDao.getFavouriteMealWithId(id)

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
            mealDetails.isFavourite = false
            shoppingDao.upsertShoppingMeal(mealDetails)
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
            mealDetails.isInCart = false
            shoppingDao.upsertShoppingMeal(mealDetails)
        }
    }

    suspend fun updateMeal(mealDetails: MealDetails) = shoppingDao.upsertShoppingMeal(mealDetails)
}