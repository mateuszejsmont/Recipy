package com.example.recipy

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.recipy.ui.navigation.RecipyNavHost

@Composable
fun RecipyApp(navController: NavHostController = rememberNavController()) {
        RecipyNavHost(navController = navController)
}