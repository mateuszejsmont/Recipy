package com.example.recipy.data

import android.content.Context
import com.example.recipy.database.AppDatabase
import com.example.recipy.database.OfflineMealsRepository
import com.example.recipy.network.MealsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val onlineMealsRepository : MealsRepository
    val offlineMealsRepository: OfflineMealsRepository
}
class DefaultAppContainer(private val context: Context) : AppContainer {
    private val baseUrl = "https://themealdb.com/api/json/v1/1/"
    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: MealsApiService by lazy {
        retrofit.create(MealsApiService::class.java)
    }

    override val onlineMealsRepository: MealsRepository by lazy {
        NetworkMealsRepository(retrofitService)
    }

    override val offlineMealsRepository: OfflineMealsRepository by lazy {
        OfflineMealsRepository(
            AppDatabase.getDatabase(context).favouritesDao(),
            AppDatabase.getDatabase(context).shoppingDao(),
            AppDatabase.getDatabase(context).ingredientDao()
        )
    }
}