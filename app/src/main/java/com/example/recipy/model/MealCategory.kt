package com.example.recipy.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealCategoriesList(
    @SerialName("meals")
    val content: List<MealCategory>,
)

@Serializable
data class MealCategory (
    @SerialName("strCategory")
    val name: String,
)