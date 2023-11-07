package com.example.recipy.ui.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipy.R
import com.example.recipy.ui.theme.RecipyTheme

@Composable
fun DishHorizontalList(dishes: List<Dish>, modifier: Modifier = Modifier){
    Column (modifier = modifier) {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = "CHICKEN",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow {
            items(dishes) { dish ->
                DishElement(
                    dish = dish,
                    modifier = Modifier.padding(horizontal = 8.dp)
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
        DishHorizontalList(dummyDishes)
    }
}