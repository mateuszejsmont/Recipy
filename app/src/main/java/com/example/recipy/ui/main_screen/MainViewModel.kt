package com.example.recipy.ui.main_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipy.data.MealsRepository
import com.example.recipy.database.OfflineMealsRepository
import com.example.recipy.model.Meal
import com.example.recipy.model.MealCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MainUiState {
    //map category to list of meals in that category
    data class Success(
        val mealsInCategories: StateFlow<Map<String, List<Meal>>>,
        val favouriteMeals: StateFlow<List<Meal>>,
        val searchText: StateFlow<String>
    ) : MainUiState

    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(
    private val onlineMealsRepository: MealsRepository,
    private val offlineMealsRepository: OfflineMealsRepository,
) : ViewModel() {
    var mainUiState: MainUiState by mutableStateOf(
        MainUiState.Loading,
        policy = structuralEqualityPolicy()
    )
        private set

    private val mealsInCategoriesCache: MutableMap<String, List<Meal>> = mutableMapOf()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _mealsInCategories = MutableStateFlow(mealsInCategoriesCache)
    val mealsInCategories = searchText.combine(_mealsInCategories) { text, mealsInCategories ->
        if (text.isBlank()) {
            mealsInCategories
        } else {
            filterMealsInCategories(mealsInCategories, text)
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
        _mealsInCategories.value
    )

    init {
        getMealsInCategories()
    }

    private fun getMealsInCategories() {
        viewModelScope.launch {
            mainUiState = MainUiState.Loading
            mainUiState = try {
                val categories = onlineMealsRepository.getAllMealCategories()
                for (category: MealCategory in categories) {
                    mealsInCategoriesCache[category.name] =
                        onlineMealsRepository.getMealsWithCategory(category.name)
                }
                MainUiState.Success(
                    mealsInCategories = mealsInCategories,
                    favouriteMeals = offlineMealsRepository.getFavouritesStream()
                        .stateIn(
                            scope = viewModelScope,
                            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                            initialValue = listOf(),
                        ),
                    searchText = searchText
                )
            } catch (e: IOException) {
                MainUiState.Error
            } catch (e: HttpException) {
                MainUiState.Error
            }
        }
    }

    fun onSearchTextChange(text: String){
        _searchText.value = text
    }

    private fun filterMealsInCategories(mealsInCategories: Map<String, List<Meal>>, query: String): Map<String, List<Meal>> {
        val filtered: MutableMap<String, List<Meal>> = mutableMapOf()
        val queryToLower = query.lowercase()
        for (category in mealsInCategories.keys) {
            if (category.lowercase().contains(queryToLower)) {
                filtered[category] = mealsInCategories[category]!!
            } else {
                val filteredCategory = mealsInCategories[category]!!.filter { it.matchesQuery(queryToLower) }
                if (filteredCategory.isNotEmpty()) {
                    filtered[category] = filteredCategory
                }
            }
        }
        return filtered
    }

    suspend fun switchInFavourites(value: Boolean, meal: Meal) {
        if (value) offlineMealsRepository.addToFavourite(meal)
        else offlineMealsRepository.removeFromFavourites(meal)
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}