package com.example.recipy.ui.create

import android.media.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipy.R
import com.example.recipy.ui.shared.SimpleTopBar
import com.example.recipy.ui.shared.TopBarActionButton
import com.example.recipy.ui.theme.RecipyTheme

@Composable
fun CreateScreen(
    modifier: Modifier = Modifier,
){
    Scaffold (
        modifier = modifier,
        topBar = { SimpleTopBar(title = stringResource(R.string.create_recipe), onBackClick = {}) },
    ) { paddingValues ->
        CreateScreenBody(modifier = Modifier
            .padding(paddingValues)
            .padding(top = 15.dp))
    }
}

@Composable
fun CreateScreenBody(
    modifier: Modifier = Modifier,
){
    Column (
        modifier = modifier
            .padding(horizontal = 15.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(text = stringResource(R.string.name), style = MaterialTheme.typography.titleMedium)
        SimpleTextField(
            value = "",
            onValueChange = {},
            hint = "Name your dish...",
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 56.dp)
        )

        Text(text = stringResource(R.string.photo), style = MaterialTheme.typography.titleMedium)
        PhotoPicker(
            photo = null,
            onClick = {},
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.ingredients),
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.weight(1f))
            TopBarActionButton(
                icon = Icons.Default.Add,
                onClick = { },
                modifier = Modifier
                    .width(42.dp)
                    .height(42.dp)
            )
        }
        Column (
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IngredientRow()
            IngredientRow()
        }

        Text(text = stringResource(R.string.instructions), style = MaterialTheme.typography.titleMedium)
        SimpleTextField(
            value = "",
            onValueChange = {},
            hint = "Put your instructions here...",
            modifier = Modifier
                .height(250.dp)
                .heightIn(max = 250.dp)
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text(stringResource(R.string.create), modifier = Modifier.padding(horizontal = 20.dp))
            }
        }
    }
}

@Composable
fun SimpleTextField(
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
){
    OutlinedTextField(
        modifier = modifier,
        placeholder = { Text(hint) },
        value = value,
        onValueChange = onValueChange,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
            focusedPlaceholderColor = MaterialTheme.colorScheme.secondary,
        ),
        shape = RoundedCornerShape(8.dp)
    )
}

@Composable
fun IngredientRow(
    modifier: Modifier = Modifier,
){
    Row (
        modifier = modifier.heightIn(max = 56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SimpleTextField(
            value = "",
            onValueChange = {},
            hint = "Ingredient",
            modifier = Modifier.weight(0.65f),
        )
        SimpleTextField(
            value = "",
            onValueChange = {},
            hint = "Quantity",
            modifier = Modifier.weight(0.35f),
        )
        TopBarActionButton(
            icon = Icons.Default.Close,
            onClick = { },
            modifier = Modifier
                .width(42.dp)
                .height(42.dp)
        )
    }
}

@Composable
fun PhotoPicker(
    onClick: () -> Unit,
    photo: Image?,
    modifier: Modifier = Modifier,
){
    Card(
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.background),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        modifier = modifier.clickable(onClick = onClick)
    ){
        Box (
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(R.drawable.no_dish_selected),
                contentDescription = "Select a dish",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.width(80.dp).height(80.dp),
                )
                Text(
                    text = "ADD IMAGE",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateScreenPreview(){
    RecipyTheme {
        CreateScreen()
    }
}
