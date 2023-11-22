package com.example.recipy.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.recipy.model.Meal
import com.example.recipy.model.MealDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface MealsDao {

    @Insert
    suspend fun addFavourite(favourites: Meal)

    @Delete
    suspend fun deleteFavourite(favourites: Meal)

    @Query("SELECT * FROM meals ORDER BY meal_name ASC")
    fun getFavourites(): Flow<List<Meal>>

    @Query("SELECT * FROM meals WHERE id = :id")
    fun getFavouriteMealWithId(id: String): Flow<Meal?>

}