package com.example.recipy.ui.detail_screen

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipy.data.MealsRepository
import com.example.recipy.database.OfflineMealsRepository
import com.example.recipy.model.MealDetails
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

    private val isFavouriteState = offlineMealsRepository.getItemStream(mealId).map { it?.isFavourite }
    private val isInCartState = offlineMealsRepository.getItemStream(mealId).map { it?.isInCart }
    
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
                    inFavourites = isFavouriteState
                        .map { it == true }
                        .stateIn(
                            scope = viewModelScope,
                            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                            initialValue = false,
                        ),
                    inCart = isInCartState
                        .map { it == true }
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
        if (value) offlineMealsRepository.addToFavourite(mealDetails)
        else offlineMealsRepository.removeFromFavourites(mealDetails.id)
    }

    suspend fun switchInCart(value: Boolean, mealDetails: MealDetails){
        if (value) offlineMealsRepository.addToCart(mealDetails)
        else offlineMealsRepository.removeFromCart(mealDetails)
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}