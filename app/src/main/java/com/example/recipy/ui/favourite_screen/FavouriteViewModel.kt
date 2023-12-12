package com.example.recipy.ui.favourite_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipy.database.OfflineMealsRepository
import com.example.recipy.model.Meal
import com.example.recipy.model.MealDetails
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed interface FavouriteUiState {
    data class Success(
        val favouriteMealsInCategories: StateFlow<Map<String, List<MealDetails>>>
    ) : FavouriteUiState
    object Loading : FavouriteUiState
}

class FavouriteViewModel(
    private val offlineMealsRepository: OfflineMealsRepository,
) : ViewModel() {
    var favouriteUiState: FavouriteUiState by mutableStateOf(
        FavouriteUiState.Loading
    )
        private set

    private val favouritesInCategories =
        offlineMealsRepository.getFavouriteStreamAsMealDetails()
            .map { favourites ->
                favourites.groupBy { it.category }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = mapOf()
            )

    init{
        getFavourites()
    }

    private fun getFavourites() {
        viewModelScope.launch {
            favouriteUiState = FavouriteUiState.Loading
            favouriteUiState = FavouriteUiState.Success(favouriteMealsInCategories = favouritesInCategories)
        }
    }

    suspend fun removeFromFavourites(meal: Meal) {
        offlineMealsRepository.removeFromFavourites(meal.id)
    }

    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }
}