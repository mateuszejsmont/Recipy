package com.example.recipy.ui.favourite_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipy.database.OfflineMealsRepository
import com.example.recipy.model.Meal
import com.example.recipy.model.MealDetails
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class FavouriteUiState(
    val favouritesInCategories: Map<String, List<MealDetails>> = mapOf()
)

class FavouriteViewModel(
    private val offlineMealsRepository: OfflineMealsRepository,
) : ViewModel() {
    val uiState: StateFlow<FavouriteUiState> =
        offlineMealsRepository.getFavouriteStreamAsMealDetails()
            .map { favourites ->
                FavouriteUiState(favourites.groupBy { it.category })
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = FavouriteUiState()
            )

    suspend fun removeFromFavourites(meal: Meal) {
        offlineMealsRepository.removeFromFavourites(meal.id)
    }

    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }
}