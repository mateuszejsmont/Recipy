package com.example.recipy.database.favourites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favourites(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "meal_name") val mealName: String
)

