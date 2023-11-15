package com.example.recipy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipy.data.NetworkMealsRepository
import com.example.recipy.ui.main_screen.MainScreen
import com.example.recipy.ui.theme.RecipyTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "MAIN_ACTIVITY"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //test code, can be removed
                    LaunchedEffect(Unit) {
                        Log.d(TAG, "Sending a web request...")

                        val mealsRepository = NetworkMealsRepository()
                        val meals = withContext(Dispatchers.IO) {
                            mealsRepository.getAllMealCategories()
                        }
                        Log.d(TAG, meals.firstOrNull()?.name ?: "null")
                    }
                    MainScreen()

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RecipyTheme {
        Greeting("Android")
    }
}