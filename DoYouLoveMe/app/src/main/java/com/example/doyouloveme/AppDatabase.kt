package com.example.doyouloveme

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BitFitEntity::class], version = 4)//changes in database need this version number incremented
abstract class AppDatabase : RoomDatabase() {

    abstract fun bitfitDao(): BitFitDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "BitFit_db"
            )
                .fallbackToDestructiveMigration()  // This will wipe the database if schema changes
                .build()
    }
}
