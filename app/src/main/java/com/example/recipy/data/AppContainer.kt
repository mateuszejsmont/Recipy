package com.example.recipy.data

import com.example.recipy.network.MealsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val mealsRepository : MealsRepository
}
class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://themealdb.com/api/json/v1/1/"
    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: MealsApiService by lazy {
        retrofit.create(MealsApiService::class.java)
    }

    override val mealsRepository: MealsRepository by lazy {
        NetworkMealsRepository(retrofitService)
    }
}