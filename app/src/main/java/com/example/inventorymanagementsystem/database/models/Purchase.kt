package com.example.inventorymanagementsystem.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Purchases")
data class Purchase(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "name")
    val productName: String? = null,
    val units: Int? = null,
    val invoiceNo: Long? = null,
    val date: String? = null
)
