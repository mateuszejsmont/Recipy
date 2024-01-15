package com.example.recipy.fake

import com.example.recipy.model.Meal
import com.example.recipy.model.MealCategory
import com.example.recipy.model.MealDetails

object FakeDataSource {
    val mealDetails1 = MealDetails(
        id = "52796",
        name = "Chicken Alfredo Primavera",
        drinkAlternate = null,
        category = "Chicken",
        area = "Italian",
        instructions = "instructions1",
        thumbUrl = "https://www.themealdb.com/images/media/meals/syqypv1486981727.jpg",
        tags = "Pasta,Meat,Dairy",
        youtubeUrl = "https://www.youtube.com/watch?v=qCIbq8HywpQ",
        ingredient1 = "Butter",
        ingredient2 = "Olive Oil",
        ingredient3 = "Chicken",
        ingredient4 = "Salt",
        ingredient5 = "Squash",
        ingredient6 = "Broccoli",
        ingredient7 = "mushrooms",
        ingredient8 = "Pepper",
        ingredient9 = "onion",
        ingredient10 = "garlic",
        ingredient11 = "red pepper flakes",
        ingredient12 = "white wine",
        ingredient13 = "milk",
        ingredient14 = "heavy cream",
        ingredient15 = "Parmesan cheese",
        ingredient16 = "bowtie pasta",
        ingredient17 = "Salt",
        ingredient18 = "Pepper",
        ingredient19 = "Parsley",
        ingredient20 = "",
        measure1 ="2 tablespoons",
        measure2 ="3 tablespoons",
        measure3 ="5 boneless",
        measure4 ="1 teaspoon",
        measure5 ="1 cut into 1/2-inch cubes",
        measure6 ="1 Head chopped",
        measure7 ="8-ounce sliced",
        measure8 ="1 red",
        measure9 ="1 chopped",
        measure10 = "3 cloves",
        measure11 = "1/2 teaspoon",
        measure12 = "1/2 cup",
        measure13 = "1/2 cup",
        measure14 = "1/2 cup",
        measure15 = "1 cup grated",
        measure16 = "16 ounces",
        measure17 = "pinch",
        measure18 = "pinch ",
        measure19 = "chopped",
        measure20 = "",
    )
    val mealDetails2 = MealDetails(
        id = "52772",
        name = "Teriyaki Chicken Casserole",
        drinkAlternate = null,
        category = "Chicken",
        area = "Japanese",
        instructions = "instructions2",
        thumbUrl = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg",
        tags = "Meat,Casserole",
        youtubeUrl = "https://www.youtube.com/watch?v=4aZr5hZXP_s",
        ingredient1 = "soy sauce",
        ingredient2 = "water",
        ingredient3 = "brown sugar",
        ingredient4 = "ground ginger",
        ingredient5 = "minced garlic",
        ingredient6 = "cornstarch",
        ingredient7 = "chicken breasts",
        ingredient8 = "stir-fry vegetables",
        ingredient9 = "brown rice",
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
        measure1 = "3/4 cup",
        measure2 = "1/2 cup",
        measure3 = "1/4 cup",
        measure4 = "1/2 teaspoon",
        measure5 = "1/2 teaspoon",
        measure6 = "4 Tablespoons",
        measure7 = "2",
        measure8 = "1 (12 oz.)",
        measure9 = "3 cups",
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

    val category1 = MealCategory(name = "Beef")
    val category2 = MealCategory(name = "Chicken")

    val meal1 = Meal(
        id = "52796",
        name = "Chicken Alfredo Primavera",
        thumbUrl = "https://www.themealdb.com/images/media/meals/syqypv1486981727.jpg"
    )

    val meal2 = Meal(
        id = "52772",
        name = "Teriyaki Chicken Casserole",
        thumbUrl = "https://www.themealdb.com/images/media/meals/wvpsxx1468256321.jpg"
    )

    val mealDetailsList = listOf(
        mealDetails1,
        mealDetails2
    )

    val mealCategoryList = listOf(
        category1,
        category2
    )

    val mealList = listOf(
        meal1,
        meal2
    )
}