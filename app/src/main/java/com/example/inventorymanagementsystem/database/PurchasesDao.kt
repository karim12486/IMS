package com.example.inventorymanagementsystem.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.inventorymanagementsystem.database.models.Purchase
import com.example.inventorymanagementsystem.database.models.Stock

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

    @Query("SELECT * FROM Purchases WHERE id = :id ")
    fun getCertainPurchase(id: Int): Purchase

    @Query("SELECT p.name AS productName, (p.units - COALESCE(s.units, 0)) AS units FROM Purchases p LEFT JOIN (SELECT name, SUM(units) AS units FROM Sales GROUP BY name) s ON p.name = s.name")
    fun getPurchasesMinusSales(): List<Stock>

    @Query("SELECT * FROM Purchases WHERE invoiceNo LIKE :searchQuery || '%'")
    fun searchPurchases(searchQuery: String): List<Purchase>

    @Query("SELECT COUNT(*) FROM Purchases WHERE invoiceNo = :invoiceNo")
    fun getPurchasesCount(invoiceNo: Int): Int
}