package com.example.recipy.ui.detail_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipy.R
import com.example.recipy.ui.theme.RecipyTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen() {
    val sheetCornerRadius = 32.dp
    val configuration = LocalConfiguration.current
    val sheetPeekHeight =
        configuration.screenHeightDp.dp - configuration.screenWidthDp.dp + sheetCornerRadius
    val image = painterResource(id = R.drawable.dummy_dish_1_original)
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = true
        )
    )

    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(sheetCornerRadius, sheetCornerRadius, 0.dp, 0.dp),
        scaffoldState = scaffoldState,
        sheetDragHandle = {
            Spacer(
                modifier = Modifier
                    .padding(12.dp)
                    .width(40.dp)
                    .height(5.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFD0DBEA))
            )
        },
        sheetPeekHeight = sheetPeekHeight,
        sheetContent = {
            SheetContent(modifier = Modifier.padding(horizontal = 16.dp, vertical = 0.dp))
        },
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
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
            Spacer(Modifier.weight(1f))

            IconButton(
                onClick = { /*TODO*/ },
                colors = IconButtonDefaults.iconButtonColors(Color.White),
                modifier = Modifier.padding(end=8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = Color(0xFF061B54)
                )
            }

            IconButton(
                onClick = { /*TODO*/ },
                colors = IconButtonDefaults.iconButtonColors(Color.White)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color(0xFF061B54)
                )
            }
        }

    }

}

@Composable
fun SheetContent(modifier: Modifier = Modifier) {
    val mealName = "Teriyaki Chicken Casserole"
    val strInstructions =
        "Preheat oven to 350° F. Spray a 9x13-inch baking pan with non-stick spray.\r\nCombine soy sauce, ½ cup water, brown sugar, ginger and garlic in a small saucepan and cover. Bring to a boil over medium heat. Remove lid and cook for one minute once boiling.\r\nMeanwhile, stir together the corn starch and 2 tablespoons of water in a separate dish until smooth. Once sauce is boiling, add mixture to the saucepan and stir to combine. Cook until the sauce starts to thicken then remove from heat.\r\nPlace the chicken breasts in the prepared pan. Pour one cup of the sauce over top of chicken. Place chicken in oven and bake 35 minutes or until cooked through. Remove from oven and shred chicken in the dish using two forks.\r\n*Meanwhile, steam or cook the vegetables according to package directions.\r\nAdd the cooked vegetables and rice to the casserole dish with the chicken. Add most of the remaining sauce, reserving a bit to drizzle over the top when serving. Gently toss everything together in the casserole dish until combined. Return to oven and cook 15 minutes. Remove from oven and let stand 5 minutes before serving. Drizzle each serving with remaining sauce. Enjoy!"
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

    Column(
        modifier = modifier
            .fillMaxSize() //makes the sheet extendable to full screen
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = mealName, style = MaterialTheme.typography.headlineMedium)
        Divider(color = Color(0xFFD0DBEA))
        Text(text = "INSTRUCTIONS", style = MaterialTheme.typography.titleMedium)
        Text(text = strInstructions, style = MaterialTheme.typography.bodySmall)
        Divider(color = Color(0xFFD0DBEA))
        Text(text = "INGREDIENTS", style = MaterialTheme.typography.titleMedium)

        for (pair in ingredients.zip(measures)) {
            if (pair.first == null || pair.second == null || pair.first == "" || pair.second == "") {
                continue
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = pair.first!!, style = MaterialTheme.typography.bodySmall,modifier = Modifier.weight(3f))
                Text(text = pair.second!!, style = MaterialTheme.typography.bodySmall, textAlign= TextAlign.End,modifier = Modifier.weight(1f))
            }
        }


    }
}

@Composable
@Preview(showBackground = true)
fun DetailScreenPreview() {
    RecipyTheme {
        DetailScreen()
    }
}