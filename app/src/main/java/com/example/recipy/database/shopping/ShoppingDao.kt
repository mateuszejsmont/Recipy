package com.example.recipy.database.shopping

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.example.recipy.model.MealDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingDao {

    @Upsert
    suspend fun upsertShoppingMeal(products: MealDetails)

    @Delete
    suspend fun deleteShoppingMeal(products: MealDetails)


    @Query("SELECT * FROM shopping_meals ORDER BY meal_name ASC")
    fun getShoppingMeals(): Flow<List<MealDetails>>

}