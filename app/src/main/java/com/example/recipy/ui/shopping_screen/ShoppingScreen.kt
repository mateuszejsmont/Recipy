package com.example.recipy.ui.shopping_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipy.AppViewModelProvider
import com.example.recipy.R
import com.example.recipy.ui.navigation.NavigationDestination
import com.example.recipy.ui.shared.ActionSnackbar
import com.example.recipy.ui.shared.EmptyBody
import com.example.recipy.ui.shared.MealHorizontalList
import com.example.recipy.ui.shared.SimpleTopBar
import com.example.recipy.ui.theme.RecipyTheme
import kotlinx.coroutines.launch

object ShoppingDestination : NavigationDestination {
    override val route = "shopping"
}

@Composable
fun ShoppingScreen(
    onMealClick: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ShoppingViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val uiState = viewModel.uiState.collectAsState()
    val shoppingListUiState = viewModel.shoppingListUiState.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(
            hostState =  snackbarHostState,
            snackbar = {  ActionSnackbar(it) }
        )},
        topBar = { SimpleTopBar(title = stringResource(R.string.shopping), onBackClick = onBackClick) }
    ) { innerPadding ->
        if (uiState.value.mealsInCart.isEmpty()){
            EmptyBody(
                stringResource(R.string.empty_shopping_msg),
                Icons.Outlined.ShoppingCart,
                modifier = Modifier.padding(innerPadding)
            )
        }
        else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(innerPadding)
            ) {
                val ingredients = shoppingListUiState.value.ingredients
                val groupedIngredients = ingredients.groupBy({ it.first.first }, { Pair(it.second, it.first.second) })
                val transformedIngredients = groupedIngredients.map { (ingredient, pairs) ->
                     val (marks, measures) = pairs.unzip()
                    Log.d("SHOPPING", measures.first())
                    Triple(ingredient, measures.joinToString(separator = " and "), marks.fold(true) {acc, i -> acc && i})
                }
                item {
                    MealHorizontalList(
                        meals = uiState.value.mealsInCart.map { it.toMeal() },
                        onMealClick = onMealClick,
                        onMealActionButtonClick = {
                            coroutineScope.launch {
                                viewModel.removeFromCart(it)

                                snackbarHostState.currentSnackbarData?.dismiss()
                                snackbarHostState.showSnackbar(
                                    message = context.resources.getString(R.string.snackbar_removed_from_cart),
                                    duration = SnackbarDuration.Short,
                                    withDismissAction = true,
                                )
                            }
                        },
                        name = stringResource(R.string.meals),
                        mealActionButtonIcon = {Icons.Default.Clear}
                    )
                    Spacer(modifier = Modifier.padding(vertical = 16.dp))
                    Text(
                        text = stringResource(R.string.to_buy),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
                items(transformedIngredients) { ingredient ->
                    IngredientRow(
                        checked = ingredient.third,
                        name = ingredient.first,
                        value = ingredient.second,
                        modifier = Modifier.padding(end = 16.dp),
                        onIngredientButtonClick = { name, checked ->
                            coroutineScope.launch {
                                viewModel.switchInMarking(
                                    value = checked,
                                    name = name,
                                    mealsInCart = uiState.value.mealsInCart
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun IngredientRow(
    checked: Boolean,
    name: String,
    value: String,
    modifier: Modifier = Modifier,
    onIngredientButtonClick: (String, Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        IconButton(
            onClick = { onIngredientButtonClick(name, !checked) },
        ) {
            if (checked) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = null,
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = null,
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.weight(3f),
                textDecoration = if (checked) TextDecoration.LineThrough else null
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(1f),
                textDecoration = if (checked) TextDecoration.LineThrough else null
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun ShoppingScreenPreview() {
    RecipyTheme {
        ShoppingScreen(
            onMealClick = {},
            onBackClick = {})
    }
}