package com.example.recipy

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.recipy.ui.detail_screen.MealDetailsViewModel
import com.example.recipy.ui.favourite_screen.FavouriteViewModel
import com.example.recipy.ui.main_screen.MainViewModel
import com.example.recipy.ui.shopping_screen.ShoppingViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            MainViewModel(
                recipyApplication().container.onlineMealsRepository,
                recipyApplication().container.offlineMealsRepository,
            )
        }
        initializer {
            FavouriteViewModel(
                recipyApplication().container.onlineMealsRepository,
                recipyApplication().container.offlineMealsRepository,
            )
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