package com.example.recipy.ui.notes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipy.R
import com.example.recipy.ui.shared.SimpleTopBar
import com.example.recipy.ui.theme.RecipyTheme

@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
){
    Scaffold (
        modifier = modifier,
        topBar = { SimpleTopBar(title = stringResource(R.string.notes), onBackClick = {}) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                shape = CircleShape
            ) {
                Icon(
                    Icons.Filled.Add,
                    stringResource(R.string.add),
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    ) { paddingValues ->
        NotesBody(modifier = Modifier.padding(paddingValues).padding(top = 15.dp))
    }
}

@Composable
fun NotesBody(
    modifier: Modifier = Modifier,
){
    Column (
        modifier = modifier
            .padding(horizontal = 15.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Note("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.")
        Note("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.")
        Note("Lorem ipsum dolor sit amet.")
    }
}

@Composable
fun Note(
    text: String,
    modifier: Modifier = Modifier,
){
    Card(
        modifier = modifier.fillMaxWidth(),
        border = BorderStroke(1.dp, colorResource(R.color.light_gray)),
    ){
        Column {
            Text(
                modifier = Modifier.fillMaxWidth().padding(15.dp),
                text = text,
            )
            Divider(color = colorResource(R.color.light_gray))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .padding(horizontal = 0.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = null,
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview(){
    RecipyTheme {
        NotesScreen()
    }
}