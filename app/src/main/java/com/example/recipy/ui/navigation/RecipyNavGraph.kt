package com.example.recipy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recipy.ui.detail_screen.DetailScreen
import com.example.recipy.ui.detail_screen.MealDetailsDestination
import com.example.recipy.ui.favourite_screen.FavouriteDestination
import com.example.recipy.ui.favourite_screen.FavouriteScreen
import com.example.recipy.ui.main_screen.MainDestination
import com.example.recipy.ui.main_screen.MainScreen
import com.example.recipy.ui.shopping_screen.ShoppingDestination
import com.example.recipy.ui.shopping_screen.ShoppingScreen


interface NavigationDestination {
    val route: String
}

@Composable
fun RecipyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = MainDestination.route,
        modifier = modifier
    ) {
        composable(route = MainDestination.route) {
            MainScreen(
                onMealClick = {
                    navController.navigate("${MealDetailsDestination.route}/${it}")
                },
                onFavouriteClick = {
                    navController.navigate(FavouriteDestination.route)
                },
                onShoppingClick = {
                    navController.navigate(ShoppingDestination.route)
                }
            )
        }
        composable(route = MealDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(MealDetailsDestination.mealIdArg) {
                type = NavType.StringType
            }
            )
        ) {
            DetailScreen(
                onAddFavourite = {},
                onAddToCart = {},
                onBackClick = {
                    navController.navigateUp()
                })
        }
        composable(route = FavouriteDestination.route) {
            FavouriteScreen(
                onMealClick = {
                    navController.navigate("${MealDetailsDestination.route}/${it}")
                },
                onBackClick = {
                    navController.navigateUp()
                })
        }
        composable(route = ShoppingDestination.route) {
            ShoppingScreen(
                onMealClick = {
                    navController.navigate("${MealDetailsDestination.route}/${it}")
                },
                onBackClick = {
                    navController.navigateUp()
                })
        }
    }
}