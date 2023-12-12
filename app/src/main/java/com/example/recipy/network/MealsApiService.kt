package com.example.recipy.network

import com.example.recipy.model.MealCategoriesList
import com.example.recipy.model.MealDetailsList
import com.example.recipy.model.MealList
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApiService{
    @GET("lookup.php")
    suspend fun getMealWithId(@Query("i") mealId: String): MealDetailsList
    @GET("search.php")
    suspend fun getMealsWithSearch(@Query("s") searchPhrase: String): MealDetailsList
    @GET("filter.php")
    suspend fun getMealsWithCategory(@Query("c") category: String): MealList
    @GET("list.php?c=list")
    suspend fun getAllMealCategories(): MealCategoriesList
}
