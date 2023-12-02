package com.example.recipy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipy.model.MealDetails

@Database(entities = [MealDetails::class], version = 3)
abstract class AppDatabase: RoomDatabase() {
    abstract fun shoppingDao(): MealDetailsDao

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