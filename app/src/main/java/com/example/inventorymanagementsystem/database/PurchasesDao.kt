package com.example.inventorymanagementsystem.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.inventorymanagementsystem.database.models.Purchase

@Dao
interface PurchasesDao {
    @Insert
    fun insertPurchase(purchase: Purchase)

    @Delete
    fun deletePurchase(purchase: Purchase)

    @Update
    fun updatePurchase(purchase: Purchase)

    @Query("SELECT * FROM Purchases")
    fun getAllPurchases(): List<Purchase>
}