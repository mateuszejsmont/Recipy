package com.example.recipy.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealList(
    @SerialName("meals")
    val content: List<Meal>
)

@Serializable
data class Meal (
    @SerialName("idMeal")
    val id: String,
    @SerialName("strMeal")
    val name: String,
    @SerialName("strMealThumb")
    var thumbUrl: String?
) {
    fun matchesQuery(query: String) : Boolean {
        return name.lowercase().contains(query)
    }
}