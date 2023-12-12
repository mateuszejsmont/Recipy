package com.example.recipy.ui.shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recipy.R
import com.example.recipy.model.Meal
import com.example.recipy.ui.theme.RecipyTheme

@Composable
fun MealElement(
    meal: Meal,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    onActionButtonClick: () -> Unit,
    actionButtonIcon: ImageVector = Icons.Default.FavoriteBorder,
) {
    Card(
        modifier = modifier
            .width(244.dp)
            .clickable(onClick = onClick),
        border = BorderStroke(1.dp, colorResource(id = R.color.light_gray)),
    ) {
        Column {
            // TODO: image displaying
            if (meal.thumbUrl == "") {
                Image(
                    painter = painterResource(id = R.drawable.dummy_dish_1),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(133.dp)
                )
            } else {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current).data(meal.thumbUrl)
                        .crossfade(true).build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(133.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .padding(horizontal = 0.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 10.dp),
                    text = meal.name,
                    style = MaterialTheme.typography.titleMedium,
                )
                IconButton(onClick = onActionButtonClick) {
                    Icon(
                        imageVector = actionButtonIcon,
                        contentDescription = null,
                    )
                }

//                Icon(
//                    imageVector = Icons.Default.FavoriteBorder,
//                    contentDescription = null,
//                    modifier = Modifier.clickable { },
//                )
//                Spacer(modifier = Modifier.width(6.dp))
//                Icon(
//                    imageVector = Icons.Default.ShoppingCart,
//                    contentDescription = null,
//                    modifier = Modifier.clickable { },
//                )
            }
        }
    }
}

@Preview
@Composable
fun DishElementPreview() {
    RecipyTheme {
        MealElement(
            meal = Meal(
                id = "1",
                name = "Teriyaki Chicken Caserolle",
                thumbUrl = ""
            ),
            onClick = {},
            onActionButtonClick = {},
        )
    }
}