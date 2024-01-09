package com.example.recipy

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.recipy.ui.theme.RecipyTheme
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalTestApi::class)
class RecipyUITests {
    @get:Rule
    val rule = createComposeRule()

    private val searchBar = hasTestTag("search_bar")
    private val favouritesButton = hasTestTag("favourites_btn")
    private val favouritesScreenScaffold = hasTestTag("favourites_screen_scaffold")
    private val shoppingButton = hasTestTag("shopping_btn")
    private val shoppingScreenScaffold = hasTestTag("shopping_screen_scaffold")
    private val mealTile = hasTestTag("meal_tile")
    private val detailsMealName = hasTestTag("details_meal_name")
    private val mealTileAddToFavButton = hasTestTag("meal_tile_add_to_fav_btn")
    private val detailsAddToCartBtn = hasTestTag("details_add_to_cart_btn")
    private val detailsBackBtn = hasTestTag("details_back_btn")

    @Test
    fun exploreSearch_showsSearch(){
        val searchPhrase = "Test Phrase"
        rule.setContent {
            RecipyTheme {
                RecipyApp()
            }
        }

        rule.waitUntilAtLeastOneExists(searchBar, WAIT_UNTIL_TIMEOUT)
        rule.onNode(searchBar).performTextInput(searchPhrase)
        rule.onNode(searchBar).assert(hasText(searchPhrase))
    }

    @Test
    fun exploreFavouritesBtn_navigatesToFavourites(){
        rule.setContent {
            RecipyTheme {
                RecipyApp()
            }
        }

        rule.waitUntilAtLeastOneExists(favouritesButton, WAIT_UNTIL_TIMEOUT)
        rule.onNode(favouritesButton).performClick()
        rule.waitUntilAtLeastOneExists(favouritesScreenScaffold, WAIT_UNTIL_TIMEOUT)
    }

    @Test
    fun exploreShoppingBtn_navigatesToShopping(){
        rule.setContent {
            RecipyTheme {
                RecipyApp()
            }
        }

        rule.waitUntilAtLeastOneExists(shoppingButton, WAIT_UNTIL_TIMEOUT)
        rule.onNode(shoppingButton).performClick()
        rule.waitUntilAtLeastOneExists(shoppingScreenScaffold, WAIT_UNTIL_TIMEOUT)
    }

    @Test
    fun exploreMealTile_navigatesToCorrectDetails(){
        val mealName = "Beef and Mustard Pie"
        rule.setContent {
            RecipyTheme {
                RecipyApp()
            }
        }

        rule.waitUntilAtLeastOneExists(mealTile, WAIT_UNTIL_TIMEOUT)
        rule.onNode(mealTile and hasText(mealName)).performClick()
        rule.waitUntilAtLeastOneExists(detailsMealName, WAIT_UNTIL_TIMEOUT)
        rule.onNode(detailsMealName and hasText(mealName)).assertExists()
    }

    @Test
    fun exploreMealTileFavouritesBtn_addsToFavouritesScreen(){
        val mealName = "Beef and Mustard Pie"
        rule.setContent {
            RecipyTheme {
                RecipyApp()
            }
        }

        rule.waitUntilAtLeastOneExists(mealTile, WAIT_UNTIL_TIMEOUT)
        val mealTile = rule.onNode(mealTile and hasText(mealName))
        mealTile.onChildren().filterToOne(mealTileAddToFavButton).performClick()

        rule.onNode(favouritesButton).performClick()
        rule.waitUntilAtLeastOneExists(favouritesScreenScaffold, WAIT_UNTIL_TIMEOUT)
        rule.onNodeWithText(mealName).assertExists()
    }

    @Test
    fun detailsShoppingBtn_AddToShoppingScreen(){
        val mealName = "Beef and Mustard Pie"
        rule.setContent {
            RecipyTheme {
                RecipyApp()
            }
        }

        rule.waitUntilAtLeastOneExists(mealTile, WAIT_UNTIL_TIMEOUT)
        rule.onNode(mealTile and hasText(mealName)).performClick()

        rule.waitUntilAtLeastOneExists(detailsAddToCartBtn, WAIT_UNTIL_TIMEOUT)
        rule.onNode(detailsAddToCartBtn).performClick()
        rule.onNode(detailsBackBtn).performClick()

        rule.waitUntilAtLeastOneExists(shoppingButton, WAIT_UNTIL_TIMEOUT)
        rule.onNode(shoppingButton).performClick()

        rule.waitUntilAtLeastOneExists(shoppingScreenScaffold, WAIT_UNTIL_TIMEOUT)
        rule.onNodeWithText(mealName).assertExists()
    }

    companion object{
        private const val WAIT_UNTIL_TIMEOUT = 5_000L
    }
}