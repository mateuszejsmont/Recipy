package com.example.recipy.database

import com.example.recipy.model.Ingredient
import com.example.recipy.model.Meal
import com.example.recipy.model.MealDetails
import kotlinx.coroutines.flow.Flow

class OfflineMealsRepository(
    private val favouritesDao: MealsDao,
    private val shoppingDao: MealDetailsDao,
    private val ingredientDao: IngredientDao
) {
    fun getFavouritesStream(): Flow<List<Meal>> = favouritesDao.getFavourites()

    fun getFavouriteItemStream(id: String): Flow<Meal?> = favouritesDao.getFavouriteMealWithId(id)

    fun getShoppingIngredientsStream(): Flow<List<Ingredient>> = ingredientDao.getIngredientsShoppingList()

    fun getCartStream(): Flow<List<MealDetails>> = shoppingDao.getShoppingMeals()

    fun getCartItemStream(id: String): Flow<MealDetails?> = shoppingDao.getShoppingMealWithId(id)

    suspend fun addToFavourite(meal: Meal) = favouritesDao.addFavourite(meal)

    suspend fun removeFromFavourites(meal: Meal) = favouritesDao.deleteFavourite(meal)

    suspend fun addToCart(mealDetails: MealDetails) = shoppingDao.upsertShoppingMeal(mealDetails)

    suspend fun removeFromCart(mealDetails: MealDetails) = shoppingDao.deleteShoppingMeal(mealDetails)

    suspend fun upsertIngredient(ingredient: Ingredient) = ingredientDao.upsertIngredient(ingredient)

    suspend fun deleteIngredient(ingredient: Ingredient) = ingredientDao.deleteIngredient(ingredient)

}