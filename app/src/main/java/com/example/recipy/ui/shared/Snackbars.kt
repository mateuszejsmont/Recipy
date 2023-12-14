package com.example.recipy.ui.shared

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun MainSnackbar(data: SnackbarData){
    Snackbar(
        snackbarData = data,
        shape = RoundedCornerShape(24.dp),
        containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
        contentColor = MaterialTheme.colorScheme.primaryContainer,
        dismissActionContentColor = MaterialTheme.colorScheme.primaryContainer,
    )
}

@Composable
fun DetailsSnackbar(data: SnackbarData){
    Snackbar(
        snackbarData = data,
        shape = RoundedCornerShape(24.dp),
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        dismissActionContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
    )
}