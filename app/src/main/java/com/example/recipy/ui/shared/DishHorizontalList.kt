package com.example.recipy.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipy.R
import com.example.recipy.ui.theme.RecipyTheme

@Composable
fun DishHorizontalList(
    name: String,
    dishes: List<Dish>,
    onDishClick: (Dish) -> Unit,
    onDishActionButtonClick: (Dish) -> Unit,
    modifier: Modifier = Modifier,
    dishActionButtonIcon: ImageVector = Icons.Default.FavoriteBorder,
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 17.dp),
            text = name,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 15.dp)
        ) {
            items(dishes) { dish ->
                DishElement(
                    dish = dish,
                    onClick = { onDishClick(dish) },
                    onActionButtonClick = { onDishActionButtonClick(dish) },
                    actionButtonIcon = dishActionButtonIcon,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DishHorizontalListPreview(){
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

    RecipyTheme {
        DishHorizontalList(
            "CHICKEN",
            dummyDishes,
            onDishClick = {},
            onDishActionButtonClick = {}
        )
    }
}