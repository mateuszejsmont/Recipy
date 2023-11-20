package com.example.recipy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealDetailsList(
    @SerialName("meals")
    val content: List<MealDetails>
)

@Entity(tableName = "shopping_meals")
@Serializable
data class MealDetails(
    @PrimaryKey
    @SerialName("idMeal")
    val id: String,
    @ColumnInfo(name = "meal_name")
    @SerialName("strMeal")
    val name: String,
    @ColumnInfo(name = "drink_alternate")
    @SerialName("strDrinkAlternate")
    val drinkAlternate: String?,
    @ColumnInfo(name = "category")
    @SerialName("strCategory")
    val category: String,
    @ColumnInfo(name = "area")
    @SerialName("strArea")
    val area: String,
    @ColumnInfo(name = "instructions")
    @SerialName("strInstructions")
    val instructions: String,
    @ColumnInfo(name = "url")
    @SerialName("strMealThumb")
    val thumbUrl: String,
    @ColumnInfo(name = "tags")
    @SerialName("strTags")
    val tags: String?,
    @ColumnInfo("youtube")
    @SerialName("strYoutube")
    val youtubeUrl: String,
    @ColumnInfo("ingredient1")
    @SerialName("strIngredient1")
    val ingredient1: String?,
    @ColumnInfo("ingredient2")
    @SerialName("strIngredient2")
    val ingredient2: String?,
    @ColumnInfo("ingredient3")
    @SerialName("strIngredient3")
    val ingredient3: String?,
    @ColumnInfo("ingredient4")
    @SerialName("strIngredient4")
    val ingredient4: String?,
    @ColumnInfo("ingredient5")
    @SerialName("strIngredient5")
    val ingredient5: String?,
    @ColumnInfo("ingredient6")
    @SerialName("strIngredient6")
    val ingredient6: String?,
    @ColumnInfo("ingredient7")
    @SerialName("strIngredient7")
    val ingredient7: String?,
    @ColumnInfo("ingredient8")
    @SerialName("strIngredient8")
    val ingredient8: String?,
    @ColumnInfo("ingredient9")
    @SerialName("strIngredient9")
    val ingredient9: String?,
    @ColumnInfo("ingredient10")
    @SerialName("strIngredient10")
    val ingredient10: String?,
    @ColumnInfo("ingredient11")
    @SerialName("strIngredient11")
    val ingredient11: String?,
    @ColumnInfo("ingredient12")
    @SerialName("strIngredient12")
    val ingredient12: String?,
    @ColumnInfo("ingredient13")
    @SerialName("strIngredient13")
    val ingredient13: String?,
    @ColumnInfo("ingredient14")
    @SerialName("strIngredient14")
    val ingredient14: String?,
    @ColumnInfo("ingredient15")
    @SerialName("strIngredient15")
    val ingredient15: String?,
    @ColumnInfo("ingredient16")
    @SerialName("strIngredient16")
    val ingredient16: String?,
    @ColumnInfo("ingredient17")
    @SerialName("strIngredient17")
    val ingredient17: String?,
    @ColumnInfo("ingredient18")
    @SerialName("strIngredient18")
    val ingredient18: String?,
    @ColumnInfo("ingredient19")
    @SerialName("strIngredient19")
    val ingredient19: String?,
    @ColumnInfo("ingredient20")
    @SerialName("strIngredient20")
    val ingredient20: String?,
    @ColumnInfo("measure1")
    @SerialName("strMeasure1")
    val measure1: String?,
    @ColumnInfo("measure2")
    @SerialName("strMeasure2")
    val measure2: String?,
    @ColumnInfo("measure3")
    @SerialName("strMeasure3")
    val measure3: String?,
    @ColumnInfo("measure4")
    @SerialName("strMeasure4")
    val measure4: String?,
    @ColumnInfo("measure5")
    @SerialName("strMeasure5")
    val measure5: String?,
    @ColumnInfo("measure6")
    @SerialName("strMeasure6")
    val measure6: String?,
    @ColumnInfo("measure7")
    @SerialName("strMeasure7")
    val measure7: String?,
    @ColumnInfo("measure8")
    @SerialName("strMeasure8")
    val measure8: String?,
    @ColumnInfo("measure9")
    @SerialName("strMeasure9")
    val measure9: String?,
    @ColumnInfo("measure10")
    @SerialName("strMeasure10")
    val measure10: String?,
    @ColumnInfo("measure11")
    @SerialName("strMeasure11")
    val measure11: String?,
    @ColumnInfo("measure12")
    @SerialName("strMeasure12")
    val measure12: String?,
    @ColumnInfo("measure13")
    @SerialName("strMeasure13")
    val measure13: String?,
    @ColumnInfo("measure14")
    @SerialName("strMeasure14")
    val measure14: String?,
    @ColumnInfo("measure15")
    @SerialName("strMeasure15")
    val measure15: String?,
    @ColumnInfo("measure16")
    @SerialName("strMeasure16")
    val measure16: String?,
    @ColumnInfo("measure17")
    @SerialName("strMeasure17")
    val measure17: String?,
    @ColumnInfo("measure18")
    @SerialName("strMeasure18")
    val measure18: String?,
    @ColumnInfo("measure19")
    @SerialName("strMeasure19")
    val measure19: String?,
    @ColumnInfo("measure20")
    @SerialName("strMeasure20")
    val measure20: String?,

    @ColumnInfo("is_favourite")
    val isFavourite: Boolean? = false,
    @ColumnInfo("is_on_buy_list")
    val isOnBuyList: Boolean? = false
)