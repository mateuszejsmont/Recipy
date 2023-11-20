package com.example.recipy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipy.database.favourites.FavouritesDao
import com.example.recipy.database.shopping.ShoppingDao
import com.example.recipy.model.Meal
import com.example.recipy.model.MealDetails

@Database(entities = [Meal::class, MealDetails::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favouritesDao(): FavouritesDao
    abstract fun shoppingDao(): ShoppingDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "app_database.db"
                        ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}