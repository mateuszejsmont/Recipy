package com.example.recipy.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.recipy.model.MealDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDetailsDao {

    @Upsert
    suspend fun upsertShoppingMeal(products: MealDetails)

    @Delete
    suspend fun deleteShoppingMeal(products: MealDetails)

    @Query("SELECT * FROM meal_details ORDER BY meal_name ASC")
    fun getShoppingMeals(): Flow<List<MealDetails>>

    @Query("SELECT * FROM meal_details WHERE id = :id")
    fun getShoppingMealWithId(id: String): Flow<MealDetails?>

    @Query("SELECT * FROM meal_details WHERE meal_name LIKE :searchPhrase")
    fun getMealsWithSearch(searchPhrase: String): Flow<List<MealDetails>>

}