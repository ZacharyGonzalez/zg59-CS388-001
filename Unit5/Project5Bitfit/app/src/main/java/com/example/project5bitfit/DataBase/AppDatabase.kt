package com.example.project5bitfit.DataBase
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.project5bitfit.DataItems.NutritionEntity

@Database(entities = [NutritionEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun nutrientDao() : NutritionDao

    companion object
    {
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context : Context) : AppDatabase =
            INSTANCE ?: synchronized(this)
            {
                return INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context : Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "Nutrient.db"
            ).build()
    }
}
