package com.example.project5bitfit.DataItems
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nutrient_table")
data class NutritionEntity (
    @ColumnInfo(name = "Food") val name: String,
    @ColumnInfo(name = "calories") val calories: String,
    @ColumnInfo(name = "time") val time: String,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)
