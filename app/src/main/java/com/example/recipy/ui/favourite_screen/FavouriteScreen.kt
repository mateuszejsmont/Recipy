package com.example.recipy.ui.favourite_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipy.R
import com.example.recipy.model.Meal
import com.example.recipy.ui.navigation.NavigationDestination
import com.example.recipy.ui.shared.MealHorizontalList
import com.example.recipy.ui.theme.RecipyTheme

val dummyMeals = listOf(
    Meal(
        id = "1",
        name = "Teriyaki Chicken Caserolle",
        thumbUrl = ""
    ),
    Meal(
        id = "1",
        name = "Polish Pancakes",
        thumbUrl = ""
    ),
    Meal(
        id = "1",
        name = "Beetroot Soup",
        thumbUrl = ""
    ),
    Meal(
        id = "1",
        name = "Sweet Potato Fries",
        thumbUrl = ""
    ),
)
val dummyLists = listOf(
    dummyMeals,
    dummyMeals,
    dummyMeals,
    dummyMeals,
)

object FavouriteDestination : NavigationDestination {
    override val route = "favourite"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(
    onMealClick: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = { FavouriteScreenTopBar(onBackClick=onBackClick, modifier = Modifier.padding(horizontal = 8.dp)) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                items(dummyLists) { meals ->
                    MealHorizontalList(
                        meals = meals,
                        name = "CHICKEN",
                        onMealClick = onMealClick,
                        onMealActionButtonClick = {},
                        mealActionButtonIcon = Icons.Filled.Favorite
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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

//@Composable
//fun TopBarActionButton(
//    icon: ImageVector,
//    onClick: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    FloatingActionButton(
//        modifier = modifier,
//        shape = CircleShape,
//        containerColor = MaterialTheme.colorScheme.primaryContainer,
//        elevation = FloatingActionButtonDefaults.elevation(
//            defaultElevation = 0.dp
//        ),
//        onClick = onClick
//    ) {
//        Icon(imageVector = icon, contentDescription = null)
//    }
//}

@Composable
@Preview(showBackground = true)
fun FavouriteScreenPreview() {
    RecipyTheme {
        FavouriteScreen(onMealClick = {}, onBackClick = {})
    }
}