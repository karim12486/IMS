package com.example.inventorymanagementsystem.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.inventorymanagementsystem.database.models.Purchase
import com.example.inventorymanagementsystem.database.models.Sale

@Dao
interface SalesDao {
    @Insert
    fun insertSale(sale: Sale)

    @Delete
    fun deleteSale(sale: Sale)

    @Update
    fun updateSale(sale: Sale)

    @Query("SELECT * FROM Sales")
    fun getAllSales(): List<Sale>

    @Query("SELECT * FROM Sales WHERE id = :id ")
    fun getCertainSale(id: Int): Sale

    @Query("SELECT * FROM Sales WHERE invoiceNo LIKE :searchQuery || '%'")
    fun searchSales(searchQuery: String): List<Sale>
}