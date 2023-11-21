package com.example.recipy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealList(
    @SerialName("meals")
    val content: List<Meal>
)

@Entity(tableName = "meals") //stores meals added to favourite
@Serializable
data class Meal (
    @PrimaryKey
    @SerialName("idMeal")
    val id: String,
    @ColumnInfo(name = "meal_name")
    @SerialName("strMeal")
    val name: String,
    @ColumnInfo(name = "url")
    @SerialName("strMealThumb")
    val thumbUrl: String,

    @ColumnInfo(name = "is_favourite")
    val isFavourite: Boolean? = false
)