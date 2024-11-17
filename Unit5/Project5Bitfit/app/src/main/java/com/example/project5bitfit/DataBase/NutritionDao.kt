package com.example.project5bitfit.DataBase
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.project5bitfit.DataItems.NutritionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NutritionDao {
    @Query("SELECT * FROM nutrient_table")
    fun getAll(): Flow<List<NutritionEntity>>

    @Insert
    fun insertAll(foods : List<NutritionEntity>)

    @Insert
    fun insert(foodEntity: NutritionEntity)

    @Query("DELETE FROM nutrient_table")
    fun deleteAll()
}
