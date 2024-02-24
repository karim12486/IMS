package com.example.inventorymanagementsystem.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.inventorymanagementsystem.database.models.Purchase
import com.example.inventorymanagementsystem.database.models.Sale

@Database(entities = [Purchase::class,Sale::class], version = 4)
abstract class ItemsDatabase : RoomDatabase() {

    abstract fun getPurchasesDao(): PurchasesDao
    abstract fun getSalesDao(): SalesDao
    companion object{
        private var INSTANCE:ItemsDatabase? = null
        private val DATABASE_NAME = "IMS Database"

        fun getInstance(context: Context):ItemsDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context, ItemsDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }
}

