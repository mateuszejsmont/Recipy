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
            MainViewModel(recipyApplication().container.mealsRepository)
        }
        initializer {
            FavouriteViewModel(recipyApplication().container.mealsRepository)
        }
        initializer {
            ShoppingViewModel(recipyApplication().container.mealsRepository)
        }
        initializer {
            MealDetailsViewModel(
                this.createSavedStateHandle(),
                recipyApplication().container.mealsRepository
            )
        }
    }
}

fun CreationExtras.recipyApplication(): RecipyApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as RecipyApplication)