package com.example.recipy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipy.database.favourites.Favourites
import com.example.recipy.database.favourites.FavouritesDao
import com.example.recipy.database.shopping.ShoppingDao
import com.example.recipy.database.shopping.ShoppingMeals
import com.example.recipy.database.shopping.ShoppingProducts

@Database(entities = [Favourites::class, ShoppingProducts::class, ShoppingMeals::class], version = 1)
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