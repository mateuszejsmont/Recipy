package com.example.recipy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.recipy.model.Ingredient
import com.example.recipy.model.Meal
import com.example.recipy.model.MealDetails

@Database(entities = [Meal::class, MealDetails::class, Ingredient::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favouritesDao(): MealsDao
    abstract fun shoppingDao(): MealDetailsDao
    abstract fun ingredientDao(): IngredientDao

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


class Converters {

    @TypeConverter
    fun listToJson(value: List<String>?) = value?.joinToString(separator = "~~~~")

    @TypeConverter
    fun jsonToList(value: String) = value.split("~~~~")
}