package com.example.recipy.network

import com.example.recipy.model.MealCategoriesList
import com.example.recipy.model.MealDetailsList
import com.example.recipy.model.MealList
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://themealdb.com/api/json/v1/1/"

private val json = Json { ignoreUnknownKeys = true }
private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

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

object MealsApi{
    val retrofitServices: MealsApiService by lazy {
        retrofit.create(MealsApiService::class.java)
    }
}

