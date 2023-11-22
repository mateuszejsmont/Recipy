package com.example.recipy.ui.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipy.data.MealsRepository
import com.example.recipy.database.OfflineMealsRepository
import com.example.recipy.model.MealDetails
import com.example.recipy.ui.detail_screen.MealDetailsDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface DetailUiState {
    data class Success(
        val mealDetails: MealDetails,
        val inFavourites: StateFlow<Boolean>,
        val inCart: StateFlow<Boolean>
    ) : DetailUiState
    object Error : DetailUiState
    object Loading : DetailUiState
}

class MealDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val onlineMealsRepository: MealsRepository,
    private val offlineMealsRepository: OfflineMealsRepository,
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
                DetailUiState.Success(
                    mealDetails = onlineMealsRepository.getMealWithId(mealId = mealId)!!,
                    inFavourites = offlineMealsRepository.getFavouriteItemStream(id = mealId)
                        .map { it != null }
                        .stateIn(
                            scope = viewModelScope,
                            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                            initialValue = false,
                        ),
                    inCart = offlineMealsRepository.getCartItemStream(id = mealId)
                        .map { it != null }
                        .stateIn(
                            scope = viewModelScope,
                            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                            initialValue = false,
                        ),
                )
            } catch (e: IOException) {
                DetailUiState.Error
            } catch (e: HttpException) {
                DetailUiState.Error
            }
        }
    }

    suspend fun switchInFavourites(value: Boolean, mealDetails: MealDetails){
        if (value) offlineMealsRepository.addToFavourite(mealDetails.toMeal())
        else offlineMealsRepository.removeFromFavourites(mealDetails.toMeal())
    }

    suspend fun switchInCart(value: Boolean, mealDetails: MealDetails){
        if (value) offlineMealsRepository.addToCart(mealDetails)
        else offlineMealsRepository.removeFromCart(mealDetails)
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}