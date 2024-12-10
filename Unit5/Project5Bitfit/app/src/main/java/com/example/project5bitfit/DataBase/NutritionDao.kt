package com.example.project5bitfit.DataBase
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.project5bitfit.DataItems.NutritionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NutrientDao {
    @Query("SELECT * FROM nutrient_table ORDER BY time ASC")
    fun getAll(): Flow<List<NutritionEntity>>

    @Insert
    suspend fun insert(entity: NutritionEntity)

    // Correcting the return type for deleteAll()
    @Query("DELETE FROM nutrient_table")
    suspend fun deleteAll(): Int // Returns the number of rows deleted.

    @Query("DELETE FROM nutrient_table WHERE id = :id")
    suspend fun delete(id: Long): Int // Returns the number of rows deleted.
}


