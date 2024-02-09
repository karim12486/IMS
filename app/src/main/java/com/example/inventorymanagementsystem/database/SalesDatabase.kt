package com.example.inventorymanagementsystem.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inventorymanagementsystem.database.models.Sale

@Database(entities = [Sale::class], version = 2)
abstract class SalesDatabase: RoomDatabase() {

    abstract fun getSalesDao(): SalesDao
    companion object{
        private var INSTANCE:SalesDatabase? = null
        private val DATABASE_NAME = "IMS Database"

        fun getInstance(context: Context):SalesDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context, SalesDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .fallbackToDestructiveMigrationOnDowngrade()
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }
}