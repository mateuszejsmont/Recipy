package com.example.recipy.fake

import com.example.recipy.data.MealsRepository
import com.example.recipy.model.Meal
import com.example.recipy.model.MealCategory
import com.example.recipy.model.MealDetails

class FakeNetworkMealsRepository : MealsRepository {
    override suspend fun getMealWithId(mealId: String): MealDetails? {
        return FakeDataSource.mealDetails1
    }

    override suspend fun getMealsWithSearch(searchPhrase: String): List<MealDetails> {
        return FakeDataSource.mealDetailsList
    }

    override suspend fun getMealsWithCategory(category: String): List<Meal> {
        return FakeDataSource.mealList
    }

    override suspend fun getAllMealCategories(): List<MealCategory> {
        return FakeDataSource.mealCategoryList
    }
}