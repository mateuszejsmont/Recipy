package com.example.recipy.database.favourites

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesDao {

    @Insert
    suspend fun addFavourite(favourites: Favourites)

    @Delete
    suspend fun deleteFavourite(favourites: Favourites)

    @Query("SELECT * FROM favourites ORDER BY meal_name ASC")
    fun getFavourites(): Flow<List<Favourites>>
}