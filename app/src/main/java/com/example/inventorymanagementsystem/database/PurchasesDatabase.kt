package com.example.inventorymanagementsystem.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inventorymanagementsystem.database.models.Purchase

@Database(entities = [Purchase::class], version = 4)
abstract class PurchasesDatabase : RoomDatabase() {

    abstract fun getPurchasesDao(): PurchasesDao
    companion object{
        private var INSTANCE:PurchasesDatabase? = null
        private val DATABASE_NAME = "IMS Database"


        fun getInstance(context: Context):PurchasesDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context, PurchasesDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }
}