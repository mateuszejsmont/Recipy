package com.example.recipy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class Ingredient(
    @PrimaryKey
    val id: Int,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("amount")
    val amount: List<String>,
    @ColumnInfo("marked")
    val marked: Boolean
)