package com.example.recipy.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipy.R
import com.example.recipy.ui.theme.RecipyTheme

@Composable
fun ErrorBody(
    text: String,
    icon: Painter,
    buttonText: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier
            .padding(horizontal = 32.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier
                .width(92.dp)
                .height(92.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(48.dp))
        Button(
            onClick = onButtonClick,
        ) {
            Text(buttonText)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ErrorBodyPreview() {
    RecipyTheme {
        ErrorBody("An internet connection error occurred.", painterResource(R.drawable.wifi_off), "Try again", {})
    }
}