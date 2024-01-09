package com.example.recipy.ui.main_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipy.AppViewModelProvider
import com.example.recipy.R
import com.example.recipy.model.Meal
import com.example.recipy.ui.navigation.NavigationDestination
import com.example.recipy.ui.shared.ActionSnackbar
import com.example.recipy.ui.shared.ErrorBody
import com.example.recipy.ui.shared.LoadingBody
import com.example.recipy.ui.shared.MealHorizontalList
import com.example.recipy.ui.shared.TopBarActionButton
import com.example.recipy.ui.theme.RecipyTheme
import kotlinx.coroutines.launch

val dummyMeals = listOf(
    Meal(
        id = "1",
        name = "Teriyaki Chicken Caserolle",
        thumbUrl = ""
    ),
    Meal(
        id = "1",
        name = "Polish Pancakes",
        thumbUrl = ""
    ),
    Meal(
        id = "1",
        name = "Beetroot Soup",
        thumbUrl = ""
    ),
    Meal(
        id = "1",
        name = "Sweet Potato Fries",
        thumbUrl = ""
    ),
)
val dummyLists = listOf(
    dummyMeals,
    dummyMeals,
    dummyMeals,
    dummyMeals,
)

val dummyMap = mapOf(
    "dupa1" to dummyMeals,
    "dupa2" to dummyMeals,
    "dupa3" to dummyMeals
)

object MainDestination : NavigationDestination {
    override val route = "main"
}

@Composable
fun MainScreen(
    onMealClick: (String) -> Unit,
    onFavouriteClick: () -> Unit,
    onShoppingClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(
            hostState =  snackbarHostState,
            snackbar = {  ActionSnackbar(it) }
        ) },
        topBar = {
            MainScreenTopBar(
                onFavouriteClick = onFavouriteClick,
                onShoppingClick = onShoppingClick,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    ) { innerPadding ->
        when (val uiState = viewModel.mainUiState) {
            is MainUiState.Error -> {
                ErrorBody(
                    stringResource(R.string.error_message),
                    icon = painterResource(R.drawable.wifi_off),
                    buttonText = stringResource(R.string.error_try_again),
                    onButtonClick = { viewModel.retryConnection() }
                )
            }
            is MainUiState.Loading -> {
                LoadingBody(modifier = Modifier.padding(innerPadding))
            }
            is MainUiState.Success -> {
                val mealsInCategories = uiState.mealsInCategories.collectAsState()
                val favouriteMeals = uiState.favouriteMeals.collectAsState()
                val searchText = uiState.searchText.collectAsState()

                MainBody(
                    mealsInCategories = mealsInCategories.value,
                    favouriteMeals = favouriteMeals.value,
                    searchText = searchText.value,
                    onMealClick = onMealClick,
                    onMealFavouriteClick = { meal ->
                        scope.launch {
                            val newValue = !favouriteMeals.value.contains(meal)
                            viewModel.switchInFavourites(
                                value = newValue,
                                id = meal.id
                            )

                            snackbarHostState.currentSnackbarData?.dismiss()
                            snackbarHostState.showSnackbar(
                                message = if (newValue) context.resources.getString(R.string.snackbar_added_to_favourites)
                                    else context.resources.getString(R.string.snackbar_removed_from_favourites),
                                duration = SnackbarDuration.Short,
                                withDismissAction = true,
                            )
                        }
                    },
                    onSearchTextChange = viewModel::onSearchTextChange,
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }
    }
}

@Composable
private fun MainBody(
    mealsInCategories: Map<String, List<Meal>>,
    favouriteMeals: List<Meal>,
    searchText: String,
    onMealClick: (String) -> Unit,
    onMealFavouriteClick: (Meal) -> Unit,
    onSearchTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = modifier.pointerInput(Unit) {detectTapGestures ( onTap =  {focusManager.clearFocus()})}
    ) {
        SearchBar(
            searchText = searchText,
            onSearchTextChange = onSearchTextChange,
            modifier = Modifier
                .padding(horizontal = 13.dp, vertical = 8.dp)
                .fillMaxWidth()
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            items(mealsInCategories.keys.toList()) { category ->
                MealHorizontalList(
                    meals = mealsInCategories[category]!!,
                    name = category,
                    onMealClick = onMealClick,
                    mealActionButtonIcon = {
                        if (favouriteMeals.contains(it)) Icons.Filled.Favorite
                        else Icons.Outlined.FavoriteBorder
                    },
                    onMealActionButtonClick = { onMealFavouriteClick(it) },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenTopBar(
    onFavouriteClick: () -> Unit,
    onShoppingClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                stringResource(R.string.explore),
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            TopBarActionButton(
                Icons.Default.FavoriteBorder,
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp)
                    .testTag("favourites_btn"),
                onClick = onFavouriteClick
            )
            Spacer(modifier = Modifier.width(10.dp))
            TopBarActionButton(
                Icons.Default.ShoppingCart,
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp)
                    .testTag("shopping_btn"),
                onClick = onShoppingClick
            )
        }
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchText: String = "",
    onSearchTextChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        modifier = modifier.testTag("search_bar"),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
            focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        trailingIcon = {
            if (searchText.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onSearchTextChange("")
                    })
            }
        },
        placeholder = {
            Text(stringResource(R.string.search), color = MaterialTheme.colorScheme.secondary)
        },
        shape = RoundedCornerShape(100),
        value = searchText,
        onValueChange = onSearchTextChange,
        singleLine = true,
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
    )
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview() {
    RecipyTheme {
        MainScreen(
            onMealClick = {},
            onFavouriteClick = {},
            onShoppingClick = {}
        )
    }
}