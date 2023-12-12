package com.example.recipy.data

import com.example.recipy.model.Meal
import com.example.recipy.model.MealCategory
import com.example.recipy.model.MealDetails
import com.example.recipy.network.MealsApiService

class NetworkMealsRepository(
    private val mealsApiService: MealsApiService
): MealsRepository{
    override suspend fun getMealsWithSearch(searchPhrase: String): List<MealDetails> {
        return mealsApiService.getMealsWithSearch(searchPhrase).content
    }

    override suspend fun getMealsWithCategory(category: String): List<Meal> {
        return mealsApiService.getMealsWithCategory(category).content
    }

    override suspend fun getMealWithId(mealId: String): MealDetails? {
        val mealList = mealsApiService.getMealWithId(mealId)
        return mealList.content.firstOrNull()
    }

    override suspend fun getAllMealCategories(): List<MealCategory> {
        return  mealsApiService.getAllMealCategories().content
    }
}