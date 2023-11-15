package com.example.recipy.data

import com.example.recipy.model.Meal
import com.example.recipy.model.MealCategoriesList
import com.example.recipy.model.MealCategory
import com.example.recipy.model.MealDetails
import com.example.recipy.model.MealDetailsList
import com.example.recipy.model.MealList

interface MealsRepository {
    suspend fun getMealWithId(mealId: String): MealDetails?
    suspend fun getMealsWithSearch(searchPhrase: String): List<MealDetails>
    suspend fun getMealsWithCategory(category: String): List<Meal>
    suspend fun getAllMealCategories(): List<MealCategory>
}