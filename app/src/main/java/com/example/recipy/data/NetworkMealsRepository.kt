package com.example.recipy.data

import com.example.recipy.model.Meal
import com.example.recipy.model.MealCategory
import com.example.recipy.model.MealDetails
import com.example.recipy.model.MealDetailsList
import com.example.recipy.model.MealList
import com.example.recipy.network.MealsApi

class NetworkMealsRepository(): MealsRepository{
    override suspend fun getMealsWithSearch(searchPhrase: String): List<MealDetails> {
        return MealsApi.retrofitServices.getMealsWithSearch(searchPhrase).content
    }

    override suspend fun getMealsWithCategory(category: String): List<Meal> {
        return MealsApi.retrofitServices.getMealsWithCategory(category).content
    }

    override suspend fun getMealWithId(mealId: String): MealDetails? {
        val mealList = MealsApi.retrofitServices.getMealWithId(mealId)
        return mealList.content.firstOrNull()
    }

    override suspend fun getAllMealCategories(): List<MealCategory> {
        return  MealsApi.retrofitServices.getAllMealCategories().content
    }
}