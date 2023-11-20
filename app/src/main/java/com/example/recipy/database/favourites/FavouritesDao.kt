package com.example.recipy.database.favourites

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.recipy.model.Meal
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesDao {

    @Insert
    suspend fun addFavourite(favourites: Meal)

    @Delete
    suspend fun deleteFavourite(favourites: Meal)

    @Query("SELECT * FROM favourite_meals ORDER BY meal_name ASC")
    fun getFavourites(): Flow<List<Meal>>
}