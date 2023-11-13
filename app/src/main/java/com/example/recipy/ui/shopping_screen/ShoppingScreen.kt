package com.example.recipy.ui.shopping_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipy.R
import com.example.recipy.ui.shared.Dish
import com.example.recipy.ui.shared.DishHorizontalList
import com.example.recipy.ui.theme.RecipyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingScreen(modifier: Modifier = Modifier) {
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
    val ingredients = arrayOf(
        "soy sauce",
        "water",
        "brown sugar",
        "ground ginger",
        "minced garlic",
        "cornstarch",
        "chicken breasts",
        "stir-fry vegetablesas dupa cycki i wagiina na rurze sie wygina",
        "brown rice",
        "",
        "",
        "",
        "",
        "",
        "",
        null,
        null,
        null,
        null,
        null
    )
    val measures = arrayOf(
        "3/4 cup",
        "1/2 cup",
        "1/4 cup",
        "1/2 teaspoon",
        "1/2 teaspoon",
        "4 Tablespoons",
        "2",
        "4 pounded to 1cm thickness",
        "3 cups",
        "",
        "",
        "",
        "",
        "",
        "",
        null,
        null,
        null,
        null,
        null,
    )
    Scaffold(
        modifier = modifier,
        topBar = { ShoppingScreenTopBar(modifier = Modifier.padding(horizontal = 8.dp)) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
        ) {
            DishHorizontalList(
                dishes = dummyDishes,
                onDishClick = {},
                onDishActionButtonClick = {},
                name = stringResource(R.string.meals),
                dishActionButtonIcon = Icons.Default.Clear
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(text = "TO BUY", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top = 16.dp))

                for (pair in ingredients.zip(measures)) {
                    if (pair.first == null || pair.second == null || pair.first == "" || pair.second == "") {
                        continue
                    }
                    IngredientRow(checked = true, name = pair.first!!, value = pair.second!!)
                }
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingScreenTopBar(modifier: Modifier = Modifier) {
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
            stringResource(R.string.shopping),
            style = MaterialTheme.typography.titleLarge
        )
    }
}


@Composable
fun IngredientRow(
    checked: Boolean,
    name: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row (
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { /*TODO*/ },
            colors = IconButtonDefaults.iconButtonColors(Color.White)
        ) {
            if (checked) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = null,
                    tint = Color(0xFF061B54)
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = null,
                    tint = Color(0xFF061B54)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(3f),
                textDecoration = if (checked) TextDecoration.LineThrough else null
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f),
                textDecoration = if (checked) TextDecoration.LineThrough else null
            )
        }
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
fun ShoppingScreenPreview() {
    RecipyTheme {
        ShoppingScreen()
    }
}