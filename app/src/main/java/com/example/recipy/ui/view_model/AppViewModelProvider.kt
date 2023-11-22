package com.example.recipy.ui.view_model

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.recipy.RecipyApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            MainViewModel(recipyApplication().container.onlineMealsRepository)
        }
        initializer {
            FavouriteViewModel(recipyApplication().container.onlineMealsRepository)
        }
        initializer {
            ShoppingViewModel(recipyApplication().container.onlineMealsRepository)
        }
        initializer {
            MealDetailsViewModel(
                this.createSavedStateHandle(),
                recipyApplication().container.onlineMealsRepository,
                recipyApplication().container.offlineMealsRepository,
            )
        }
    }
}

fun CreationExtras.recipyApplication(): RecipyApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RecipyApplication)