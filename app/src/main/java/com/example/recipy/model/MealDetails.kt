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

@Entity(tableName = "meal_details") // stores meal details added to shopping list
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
    var thumbUrl: String,
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
    var marked1: Boolean? = false,
    var marked2: Boolean? = false,
    var marked3: Boolean? = false,
    var marked4: Boolean? = false,
    var marked5: Boolean? = false,
    var marked6: Boolean? = false,
    var marked7: Boolean? = false,
    var marked8: Boolean? = false,
    var marked9: Boolean? = false,
    var marked10: Boolean? = false,
    var marked11: Boolean? = false,
    var marked12: Boolean? = false,
    var marked13: Boolean? = false,
    var marked14: Boolean? = false,
    var marked15: Boolean? = false,
    var marked16: Boolean? = false,
    var marked17: Boolean? = false,
    var marked18: Boolean? = false,
    var marked19: Boolean? = false,
    var marked20: Boolean? = false,
    @ColumnInfo("is_favourite")
    var isFavourite: Boolean? = false,
    @ColumnInfo("is_in_cart")
    var isInCart: Boolean? = false
) {
    fun toMeal(): Meal {
        return Meal(id, name, thumbUrl)
    }

    fun getNonNullIngredientsWithMeasures(): List<Pair<Pair<String, String>, Boolean>> {
        val ingredients = getIngredients().filterNotNull().filter { it != "" }
        val measures = getMeasures().filterNotNull().filter { it != "" }
        val marked = getMarks().filterNotNull()
        return ingredients.zip(measures).zip(marked)
    }

    fun getIngredients(): List<String?> {
        return listOf(
            ingredient1,
            ingredient2,
            ingredient3,
            ingredient4,
            ingredient5,
            ingredient6,
            ingredient7,
            ingredient8,
            ingredient9,
            ingredient10,
            ingredient11,
            ingredient12,
            ingredient13,
            ingredient14,
            ingredient15,
            ingredient16,
            ingredient17,
            ingredient18,
            ingredient19,
            ingredient20
        )
    }

    fun getMeasures(): List<String?> {
        return listOf(
            measure1,
            measure2,
            measure3,
            measure4,
            measure5,
            measure6,
            measure7,
            measure8,
            measure9,
            measure10,
            measure11,
            measure12,
            measure13,
            measure14,
            measure15,
            measure16,
            measure17,
            measure18,
            measure19,
            measure20
        )
    }

    fun getMarks(): List<Boolean?> {
        return listOf(
            marked1,
            marked2,
            marked3,
            marked4,
            marked5,
            marked6,
            marked7,
            marked8,
            marked9,
            marked10,
            marked11,
            marked12,
            marked13,
            marked14,
            marked15,
            marked16,
            marked17,
            marked18,
            marked19,
            marked20
        )
    }

    fun mark(name: String, mark: Boolean): Boolean {
        var a = false
        if (ingredient1 == name) {
            marked1 = mark
            a = true
        }
        if (ingredient2 == name) {
            marked2 = mark
            a = true
        }
        if (ingredient3 == name) {
            marked3 = mark
            a = true
        }
        if (ingredient4 == name) {
            marked4 = mark
            a = true
        }
        if (ingredient5 == name) {
            marked5 = mark
            a = true
        }
        if (ingredient6 == name) {
            marked6 = mark
            a = true
        }
        if (ingredient7 == name) {
            marked7 = mark
            a = true
        }
        if (ingredient8 == name) {
            marked8 = mark
            a = true
        }
        if (ingredient9 == name) {
            marked9 = mark
            a = true
        }
        if (ingredient10 == name) {
            marked10 = mark
            a = true
        }
        if (ingredient11 == name) {
            marked11 = mark
            a = true
        }
        if (ingredient12 == name) {
            marked12 = mark
            a = true
        }
        if (ingredient13 == name) {
            marked13 = mark
            a = true
        }
        if (ingredient14 == name) {
            marked14 = mark
            a = true
        }
        if (ingredient15 == name) {
            marked15 = mark
            a = true
        }
        if (ingredient16 == name) {
            marked16 = mark
            a = true
        }
        if (ingredient17 == name) {
            marked17 = mark
            a = true
        }
        if (ingredient18 == name) {
            marked18 = mark
            a = true
        }
        if (ingredient19 == name) {
            marked19 = mark
            a = true
        }
        if (ingredient20 == name) {
            marked20 = mark
            a = true
        }
        return a
    }
}