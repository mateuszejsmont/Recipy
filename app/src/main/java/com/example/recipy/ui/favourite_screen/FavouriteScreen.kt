package com.example.recipy.ui.favourite_screen

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipy.R
import com.example.recipy.ui.shared.Dish
import com.example.recipy.ui.shared.DishElement
import com.example.recipy.ui.shared.DishHorizontalList
import com.example.recipy.ui.theme.RecipyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(modifier: Modifier = Modifier){
    val dummyDishes = listOf(
        Dish(
            name = "Teriyaki Chicken Caserolle",
            imageResourceId = R.drawable.dummy_dish_1
        ),
        Dish(
            name = "Polish Pancakes",
            imageResourceId = R.drawable.dummy_dish_2
        ),
        Dish(
            name = "Beetroot Soup",
            imageResourceId = R.drawable.dummy_dish_3
        ),
        Dish(
            name = "Sweet Potato Fries",
            imageResourceId = R.drawable.dummy_dish_4
        ),
    )
    val dummyLists = listOf(
        dummyDishes,
        dummyDishes,
        dummyDishes,
        dummyDishes,
    )

    Scaffold (
        modifier = modifier,
        topBar = { FavouriteScreenTopBar(modifier = Modifier.padding(horizontal = 8.dp)) }
    ) { innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding)
        ){
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                contentPadding = PaddingValues(vertical = 12.dp)
            ) {
                items(dummyLists){ dishes ->
                    DishHorizontalList(
                        dishes = dishes,
                        name = "CHICKEN",
                        onDishClick = {},
                        onDishActionButtonClick = {},
                        dishActionButtonIcon = Icons.Filled.Favorite
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreenTopBar(modifier: Modifier = Modifier){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        IconButton(
            onClick = { /*TODO*/ },
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

@Composable
fun TopBarActionButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    FloatingActionButton(
        modifier = modifier,
        shape = CircleShape,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp
        ),
        onClick = onClick
    ) {
        Icon(imageVector = icon, contentDescription = null)
    }
}
@Composable
@Preview(showBackground = true)
fun FavouriteScreenPreview(){
    RecipyTheme {
        FavouriteScreen()
    }
}