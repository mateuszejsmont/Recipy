package com.example.recipy.ui.shopping_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipy.data.MealsRepository
import com.example.recipy.database.OfflineMealsRepository
import com.example.recipy.model.Meal
import com.example.recipy.model.MealDetails
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class ShoppingUiState(
    val mealsInCart: List<MealDetails> = listOf()
)

data class ShoppingListUiState(
    val ingredients: List<Pair<Pair<String, String>, Boolean>> = listOf()
)

class ShoppingViewModel(
    private val offlineMealsRepository: OfflineMealsRepository
) : ViewModel() {
    val uiState: StateFlow<ShoppingUiState> =
        offlineMealsRepository.getCartStream()
            .filterNotNull()
            .map {
                ShoppingUiState(mealsInCart = it)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ShoppingUiState()
            )

    val shoppingListUiState: StateFlow<ShoppingListUiState> =
        offlineMealsRepository.getCartStream()
            .filterNotNull()
            .map { list -> list.flatMap {
                    it.getNonNullIngredientsWithMeasures()
                }
            }.map { ShoppingListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ShoppingListUiState()
            )

    suspend fun removeFromCart(meal: Meal) {
        offlineMealsRepository.removeFromCart(meal.id)
    }

    suspend fun switchInMarking(value: Boolean, name: String, mealsInCart: List<MealDetails>){
        mealsInCart.forEach { if(it.mark(name, value)) { offlineMealsRepository.updateMeal(it) } }
    }

    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }
}