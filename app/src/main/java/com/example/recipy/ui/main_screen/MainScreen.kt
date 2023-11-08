package com.example.recipy.ui.main_screen

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
fun MainScreen(modifier: Modifier = Modifier){
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
        topBar = { MainScreenTopBar(modifier = Modifier.padding(horizontal = 8.dp)) }
    ) { innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding)
        ){
            SearchBar(modifier = Modifier
                .padding(horizontal = 13.dp, vertical = 8.dp)
                .fillMaxWidth())
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
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenTopBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = { Text(
            stringResource(R.string.explore),
            style = MaterialTheme.typography.titleLarge
        ) },
        actions = {
            TopBarActionButton(
                Icons.Default.FavoriteBorder,
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp),
                onClick = {}
            )
            Spacer(modifier = Modifier.width(10.dp))
            TopBarActionButton(
                Icons.Default.ShoppingCart,
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp),
                onClick = {}
            )
        }
    )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier){
    OutlinedTextField(
        modifier = modifier,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        placeholder = {
            Text("search", color = MaterialTheme.colorScheme.secondary)
        },
        shape = RoundedCornerShape(100),
        value = "",
        onValueChange = {}
    )
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview(){
    RecipyTheme {
        MainScreen()
    }
}