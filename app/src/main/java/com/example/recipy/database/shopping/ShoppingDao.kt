package com.example.recipy.database.shopping

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {

    @Upsert
    suspend fun upsertProducts(products: ShoppingProducts)

    @Delete
    suspend fun deleteProducts(products: ShoppingProducts)

    @Insert
    suspend fun insertMeal(meals: ShoppingMeals)

    @Delete
    suspend fun deleteMeals(meals: ShoppingMeals)

    @Query("SELECT * FROM shopping_meals ORDER BY meal_name ASC")
    fun getShoppingMeals(): Flow<List<ShoppingMeals>>

    @Query("SELECT * FROM shopping_products ORDER BY product_name ASC")
    fun getShoppingProducts(): Flow<List<ShoppingProducts>>

}