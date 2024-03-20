package com.example.recipy.ui.detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recipy.AppViewModelProvider
import com.example.recipy.R
import com.example.recipy.model.MealDetails
import com.example.recipy.ui.navigation.NavigationDestination
import com.example.recipy.ui.shared.ActionSnackbar
import com.example.recipy.ui.shared.ErrorBody
import com.example.recipy.ui.shared.LoadingBody
import com.example.recipy.ui.shared.TopBarActionButton
import com.example.recipy.ui.theme.RecipyTheme
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Note
import androidx.compose.material.icons.automirrored.outlined.Note
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Note


object MealDetailsDestination : NavigationDestination {
    override val route = "meal_detail"
    const val mealIdArg = "mealId"
    val routeWithArgs = "$route/{$mealIdArg}"
}

@Composable
fun DetailScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MealDetailsViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    )
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    when (val uiState = viewModel.detailUiState) {
        is DetailUiState.Loading -> {
            LoadingBody(modifier = modifier)
        }

        is DetailUiState.Error -> {
            ErrorBody(
                stringResource(R.string.error_message),
                icon = painterResource(R.drawable.wifi_off),
                buttonText = stringResource(R.string.error_back),
                onButtonClick = onBackClick
            )
        }

        is DetailUiState.Success -> {
            val inFavouritesState = uiState.inFavourites.collectAsState()
            val inCartState = uiState.inCart.collectAsState()

            DetailBody(
                mealDetails = uiState.mealDetails,
                inFavourites = inFavouritesState.value,
                inCart = inCartState.value,
                onSwitchFavourite = {
                    coroutineScope.launch {
                        val newValue = !inFavouritesState.value
                        viewModel.switchInFavourites(newValue, uiState.mealDetails)

                        snackbarHostState.currentSnackbarData?.dismiss()
                        snackbarHostState.showSnackbar(
                            message = if (newValue) context.resources.getString(R.string.snackbar_added_to_favourites)
                            else context.resources.getString(R.string.snackbar_removed_from_favourites),
                            duration = SnackbarDuration.Short,
                            withDismissAction = true,
                        )
                    }
                },
                onSwitchInCart = {
                    coroutineScope.launch {
                        val newValue = !inCartState.value
                        viewModel.switchInCart(newValue, uiState.mealDetails)

                        snackbarHostState.currentSnackbarData?.dismiss()
                        snackbarHostState.showSnackbar(
                            message = if (newValue) context.resources.getString(R.string.snackbar_added_to_cart)
                            else context.resources.getString(R.string.snackbar_removed_from_cart),
                            duration = SnackbarDuration.Short,
                            withDismissAction = true,
                        )
                    }
                },
                onBackClick = onBackClick,
                snackbarHostState = snackbarHostState,
                modifier = modifier
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailBody(
    mealDetails: MealDetails,
    snackbarHostState: SnackbarHostState,
    inFavourites: Boolean,
    inCart: Boolean,
    onSwitchFavourite: () -> Unit,
    onSwitchInCart: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val sheetCornerRadius = 32.dp
    val configuration = LocalConfiguration.current
    val sheetPeekHeight =
        configuration.screenHeightDp.dp - configuration.screenWidthDp.dp + sheetCornerRadius
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = true
        )
    )

    BottomSheetScaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { ActionSnackbar(it) }
            )
        },
        sheetShape = RoundedCornerShape(sheetCornerRadius, sheetCornerRadius, 0.dp, 0.dp),
        scaffoldState = scaffoldState,
        sheetDragHandle = {
            Spacer(
                modifier = Modifier
                    .padding(12.dp)
                    .width(40.dp)
                    .height(5.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFD0DBEA))
            )
        },
        sheetPeekHeight = sheetPeekHeight,
        sheetContent = {
            SheetContent(
                mealDetails = mealDetails,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 0.dp)
            )
        },
        modifier = modifier,
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = ImageRequest.Builder(LocalContext.current).data(mealDetails.thumbUrl)
                .crossfade(true).build(),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
        // for preview
        //Image(contentScale = ContentScale.FillWidth, painter = painterResource(id = R.drawable.dummy_dish_1_original), contentDescription = null, modifier = Modifier.fillMaxWidth())
        TopButtons(
            inFavourites = inFavourites,
            inCart = inCart,
            onAddFavourite = { onSwitchFavourite() },
            onAddToCart = { onSwitchInCart() },
            onBackClick = onBackClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        )
    }
}

@Composable
fun SheetContent(mealDetails: MealDetails, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize() //makes the sheet extendable to full screen
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = mealDetails.name, style = MaterialTheme.typography.headlineMedium, modifier = Modifier.testTag("details_meal_name"))
        Divider(color = Color(0xFFD0DBEA))
        Text(text = stringResource(R.string.instructions), style = MaterialTheme.typography.titleMedium)
        Text(text = mealDetails.instructions, style = MaterialTheme.typography.bodySmall)
        Divider(color = Color(0xFFD0DBEA))
        Text(text = stringResource(R.string.ingredients), style = MaterialTheme.typography.titleMedium)

        for (pair in mealDetails.getIngredients().zip(mealDetails.getMeasures())) {
            if (pair.first == null || pair.second == null || pair.first == "" || pair.second == "") {
                continue
            }
            IngredientWithMeasure(
                ingredient = pair.first!!,
                measure = pair.second!!,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun TopButtons(
    inFavourites: Boolean,
    inCart: Boolean,
    onAddFavourite: () -> Unit,
    onAddToCart: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        TopBarActionButton(
            icon = Icons.AutoMirrored.Filled.ArrowBack, onClick = onBackClick, modifier = Modifier
                .height(48.dp)
                .width(48.dp)
                .testTag("details_back_btn")
        )
        Spacer(Modifier.weight(1f))

        TopBarActionButton(
            icon = Icons.AutoMirrored.Outlined.Note,
            onClick = {},
            modifier = Modifier
                .padding(end = 8.dp)
                .height(48.dp)
                .width(48.dp),
        )
        TopBarActionButton(
            icon = if (inFavourites) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            onClick = onAddFavourite,
            modifier = Modifier
                .padding(end = 8.dp)
                .height(48.dp)
                .width(48.dp)
        )
        TopBarActionButton(
            icon = if (inCart) Icons.Filled.ShoppingCart else Icons.Outlined.ShoppingCart,
            onClick = onAddToCart,
            modifier = Modifier
                .height(48.dp)
                .width(48.dp)
                .testTag("details_add_to_cart_btn")
        )
    }
}

@Composable
private fun IngredientWithMeasure(ingredient: String, measure: String, modifier: Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = ingredient,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(3f)
        )
        Text(
            text = measure,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DetailScreenPreview() {
    val mealDetails = MealDetails(
        id = "52804",
        name = "Poutine",
        drinkAlternate = null,
        category = "Miscellaneous",
        area = "Canadian",
        instructions = "Heat oil in a deep fryer or deep heavy skillet to 365°F (185°C).\r\nWarm gravy in saucepan or microwave.\r\nPlace the fries into the hot oil, and cook until light brown, about 5 minutes.\r\nRemove to a paper towel lined plate to drain.\r\nPlace the fries on a serving platter, and sprinkle the cheese over them.\r\nLadle gravy over the fries and cheese, and serve immediately.",
        thumbUrl = "https://www.themealdb.com/images/media/meals/uuyrrx1487327597.jpg",
        tags = "UnHealthy,Speciality,HangoverFood",
        youtubeUrl = "https://www.youtube.com/watch?v=UVAMAoA2_WU",
        ingredient1 = "Vegetable Oil",
        ingredient2 = "Vegetable Oil",
        ingredient3 = "Vegetable Oil",
        ingredient4 = "Vegetable Oil",
        ingredient5 = "Vegetable Oil",
        ingredient6 = "Vegetable Oil",
        ingredient7 = "Vegetable Oil",
        ingredient8 = "Vegetable Oil",
        ingredient9 = "",
        ingredient10 = "",
        ingredient11 = "",
        ingredient12 = "",
        ingredient13 = "",
        ingredient14 = "",
        ingredient15 = "",
        ingredient16 = "",
        ingredient17 = "",
        ingredient18 = "",
        ingredient19 = "",
        ingredient20 = "",
        measure1 = "Dash",
        measure2 = "Dash",
        measure3 = "Dash",
        measure4 = "Dash",
        measure5 = "Dash",
        measure6 = "Dash",
        measure7 = "Dash",
        measure8 = "Dash",
        measure9 = "",
        measure10 = "",
        measure11 = "",
        measure12 = "",
        measure13 = "",
        measure14 = "",
        measure15 = "",
        measure16 = "",
        measure17 = "",
        measure18 = "",
        measure19 = "",
        measure20 = "",
    )
    RecipyTheme (useDarkTheme = false){
        DetailBody(
            mealDetails = mealDetails,
            inFavourites = false,
            inCart = false,
            onSwitchFavourite = {},
            onSwitchInCart = {},
            onBackClick = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}
