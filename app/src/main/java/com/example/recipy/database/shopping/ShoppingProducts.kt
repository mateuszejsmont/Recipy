package com.example.recipy.database.shopping

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = ShoppingMeals::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("idMeal"),
    onDelete = ForeignKey.CASCADE)],
    tableName = "shopping_products"
)
data class ShoppingProducts(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "product_name") val productName: String,
    @ColumnInfo(name = "product_amount") val productAmount: String,
    @ColumnInfo(name = "marked") val marked: Boolean,
    @ColumnInfo(name = "id_meal") val idMeal: Int
)
