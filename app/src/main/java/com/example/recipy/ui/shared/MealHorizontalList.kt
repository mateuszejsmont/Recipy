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
import com.example.recipy.model.Meal
import com.example.recipy.ui.theme.RecipyTheme

@Composable
fun MealHorizontalList(
    name: String,
    meals: List<Meal>,
    onMealClick: (String) -> Unit,
    onMealActionButtonClick: (Meal) -> Unit,
    modifier: Modifier = Modifier,
    mealActionButtonIcon: ImageVector = Icons.Default.FavoriteBorder,
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
            items(meals) { meal ->
                MealElement(
                    meal = meal,
                    onClick = { onMealClick(meal.id) },
                    onActionButtonClick = { onMealActionButtonClick(meal) },
                    actionButtonIcon = mealActionButtonIcon,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealHorizontalListPreview(){
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

    RecipyTheme {
        MealHorizontalList(
            "CHICKEN",
            dummyMeals,
            onMealClick = {},
            onMealActionButtonClick = {}
        )
    }
}