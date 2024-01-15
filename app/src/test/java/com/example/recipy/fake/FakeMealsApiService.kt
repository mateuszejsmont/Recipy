package com.example.recipy.fake

import com.example.recipy.model.MealCategoriesList
import com.example.recipy.model.MealDetailsList
import com.example.recipy.model.MealList
import com.example.recipy.network.MealsApiService

class FakeMealsApiService : MealsApiService {
    override suspend fun getMealWithId(mealId: String): MealDetailsList {
        return MealDetailsList(content = FakeDataSource.mealDetailsList)
    }

    override suspend fun getMealsWithSearch(searchPhrase: String): MealDetailsList {
        return MealDetailsList(content = FakeDataSource.mealDetailsList)
    }

    override suspend fun getMealsWithCategory(category: String): MealList {
        return MealList(content = FakeDataSource.mealList)
    }

    override suspend fun getAllMealCategories(): MealCategoriesList {
        return MealCategoriesList(content = FakeDataSource.mealCategoryList)
    }

}