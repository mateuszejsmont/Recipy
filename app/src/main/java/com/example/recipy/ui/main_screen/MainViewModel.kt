package com.example.recipy.ui.main_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipy.data.MealsRepository
import com.example.recipy.database.OfflineMealsRepository
import com.example.recipy.model.Meal
import com.example.recipy.model.MealCategory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MainUiState {
    //map category to list of meals in that category
    data class Success(
        val mealsInCategories: Map<String, List<Meal>>,
        val favouriteMeals: StateFlow<List<Meal>>,
    ) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(
    private val onlineMealsRepository: MealsRepository,
    private val offlineMealsRepository: OfflineMealsRepository,
) : ViewModel() {
    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading, policy = structuralEqualityPolicy())
        private set

    //this can be used for fav filtering and then updating the state
    private var mealsInCategories: MutableMap<String, List<Meal>> = mutableMapOf()

    init {
        getMealsInCategories()
    }

    private fun getMealsInCategories() {
        viewModelScope.launch {
            mainUiState = MainUiState.Loading
            mainUiState = try {
                val categories = onlineMealsRepository.getAllMealCategories()
                for (category: MealCategory in categories) {
                    mealsInCategories[category.name] = onlineMealsRepository.getMealsWithCategory(category.name)
                }
                MainUiState.Success(
                    mealsInCategories = mealsInCategories,
                    favouriteMeals = offlineMealsRepository.getFavouritesStream()
                        .stateIn(
                            scope = viewModelScope,
                            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                            initialValue = listOf(),
                        )
                )
            } catch (e: IOException) {
                MainUiState.Error
            } catch (e: HttpException) {
                MainUiState.Error
            }
        }
    }

    suspend fun switchInFavourites(value: Boolean, meal: Meal){
        if (value) offlineMealsRepository.addToFavourite(meal)
        else offlineMealsRepository.removeFromFavourites(meal)
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}