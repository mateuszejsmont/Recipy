package com.example.recipy

import com.example.recipy.data.NetworkMealsRepository
import com.example.recipy.fake.FakeDataSource
import com.example.recipy.fake.FakeMealsApiService
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.assertEquals
class NetworkMealsRepositoryTest {
    @Test
    fun networkMealsRepository_getMealsWithSearch_verifyMealDetailsList() =
        runTest {
        val repository = NetworkMealsRepository(
            mealsApiService = FakeMealsApiService()
        )
        assertEquals(FakeDataSource.mealDetailsList, repository.getMealsWithSearch("searchPhrase"))
    }

    @Test
    fun networkMealsRepository_getMealsWithCategory_verifyMealList() =
        runTest {
            val repository = NetworkMealsRepository(
                mealsApiService = FakeMealsApiService()
            )
            assertEquals(FakeDataSource.mealList, repository.getMealsWithCategory("category"))
        }

    @Test
    fun networkMealsRepository_getMealWithId_verifyMealDetails() =
        runTest {
            val repository = NetworkMealsRepository(
                mealsApiService = FakeMealsApiService()
            )
            assertEquals(FakeDataSource.mealDetailsList.firstOrNull(), repository.getMealWithId("1234"))
        }

    @Test
    fun networkMealsRepository_getAllMealCategories_verifyMealCategoryList() =
        runTest {
            val repository = NetworkMealsRepository(
                mealsApiService = FakeMealsApiService()
            )
            assertEquals(FakeDataSource.mealCategoryList, repository.getAllMealCategories())
        }
}