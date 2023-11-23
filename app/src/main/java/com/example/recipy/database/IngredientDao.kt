package com.example.recipy.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.recipy.model.Ingredient
import kotlinx.coroutines.flow.Flow


@Dao
interface IngredientDao {
    @Upsert
    suspend fun upsertIngredient(ingredient: Ingredient)

    @Delete
    suspend fun deleteIngredient(ingredient: Ingredient)

    @Query("SELECT * FROM ingredients")
    fun getIngredientsShoppingList(): Flow<List<Ingredient>>
}