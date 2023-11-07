package com.example.recipy.ui.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipy.R
import com.example.recipy.ui.theme.RecipyTheme

@Composable
fun DishHorizontalList(modifier: Modifier = Modifier){
    val itemsList = (1..5).toList()

    Column (modifier = modifier) {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = "CHICKEN",
            fontFamily = FontFamily(Font(R.font.roboto_medium)),
            fontSize = 16.sp,
            lineHeight = 18.sp,
            letterSpacing = 0.sp,
            color = colorResource(id = R.color.dark_blue),
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow {
            items(itemsList) { _ ->
                DishElement(modifier = Modifier.padding(horizontal =  8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DishHorizontalListPreview(){
    RecipyTheme {
        DishHorizontalList()
    }
}