package com.example.recipy.ui.favourite_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipy.data.MealsRepository
import com.example.recipy.database.OfflineMealsRepository
import com.example.recipy.model.Meal
import com.example.recipy.model.MealDetails
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class FavouritesUiState(
    val favouriteMeals: Map<String, List<MealDetails>> = mapOf(),
)

class FavouriteViewModel(
    private val onlineMealsRepository: MealsRepository,
    private val offlineMealsRepository: OfflineMealsRepository,
) : ViewModel() {
    val uiState: StateFlow<FavouritesUiState> =
        offlineMealsRepository.getFavouritesStream()
            .map { meals ->
                //TODO: favourite meals should be saved as MealDetails, not Meals, then this api call will no longer be needed
                val details = meals.mapNotNull { onlineMealsRepository.getMealWithId(it.id) }
                FavouritesUiState(favouriteMeals = details.groupBy{ it.category })
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = FavouritesUiState()
            )

    suspend fun removeFromFavourites(meal: Meal) {
        offlineMealsRepository.removeFromFavourites(meal.id)
    }

    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }
}