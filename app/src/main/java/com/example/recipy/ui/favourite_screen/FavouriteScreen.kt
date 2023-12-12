package com.example.recipy.ui.favourite_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipy.AppViewModelProvider
import com.example.recipy.R
import com.example.recipy.model.Meal
import com.example.recipy.model.MealDetails
import com.example.recipy.ui.navigation.NavigationDestination
import com.example.recipy.ui.shared.EmptyBody
import com.example.recipy.ui.shared.LoadingBody
import com.example.recipy.ui.shared.MealHorizontalList
import kotlinx.coroutines.launch

object FavouriteDestination : NavigationDestination {
    override val route = "favourite"
}

@Composable
fun FavouriteScreen(
    onMealClick: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FavouriteViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        topBar = {
            FavouriteScreenTopBar(
                onBackClick = onBackClick,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    ) { innerPadding ->
        when (val uiState = viewModel.favouriteUiState) {
            is FavouriteUiState.Loading -> {
                LoadingBody(modifier = Modifier.padding(innerPadding))
            }

            is FavouriteUiState.Success -> {
                val favouritesInCategories = uiState.favouriteMealsInCategories.collectAsState()
                if (favouritesInCategories.value.isEmpty()) {
                    EmptyBody(
                        stringResource(R.string.empty_favourites_msg),
                        Icons.Default.FavoriteBorder,
                        modifier = Modifier.padding(innerPadding)
                    )
                } else {
                    FavouriteBody(
                        mealsInCategories = favouritesInCategories.value,
                        onMealActionButtonClick = { meal ->
                            coroutineScope.launch {
                                viewModel.removeFromFavourites(meal)
                            }
                        },
                        onMealClick = onMealClick,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
private fun FavouriteBody(
    mealsInCategories: Map<String, List<MealDetails>>,
    onMealActionButtonClick: (Meal) -> Unit,
    onMealClick: (String) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        items(mealsInCategories.keys.toList()) { category ->
            MealHorizontalList(
                meals = mealsInCategories[category]!!.map { it.toMeal() },
                name = category,
                onMealClick = onMealClick,
                onMealActionButtonClick = onMealActionButtonClick,
                mealActionButtonIcon = { Icons.Filled.Favorite }
            )
        }
    }

}

@Composable
fun FavouriteScreenTopBar(onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        IconButton(
            onClick = onBackClick,
            colors = IconButtonDefaults.iconButtonColors(Color.White),
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null,
                tint = Color(0xFF061B54)
            )
        }
        Text(
            stringResource(R.string.favourites),
            style = MaterialTheme.typography.titleLarge
        )
    }
}