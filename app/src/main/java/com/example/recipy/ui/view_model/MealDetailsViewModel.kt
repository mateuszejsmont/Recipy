package com.example.recipy.ui.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipy.data.MealsRepository
import com.example.recipy.model.MealDetails
import com.example.recipy.ui.detail_screen.MealDetailsDestination
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface DetailUiState {
    data class Success(val mealDetails: MealDetails) : DetailUiState
    object Error : DetailUiState
    object Loading : DetailUiState
}

class MealDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val mealsRepository: MealsRepository
) : ViewModel() {
    private val mealId: String = checkNotNull(savedStateHandle[MealDetailsDestination.mealIdArg])

    var detailUiState : DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    init {
        getMealDetails()
    }

    private fun getMealDetails() {
        viewModelScope.launch {
            detailUiState = DetailUiState.Loading
            detailUiState = try {
                DetailUiState.Success(mealsRepository.getMealWithId(mealId = mealId)!!)
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }
}