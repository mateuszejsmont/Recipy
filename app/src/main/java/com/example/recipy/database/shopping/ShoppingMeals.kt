package com.example.recipy.database.shopping

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_meals")
data class ShoppingMeals(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "meal_name") val mealName: String
)

