package com.example.inventorymanagementsystem.fragments

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import com.example.inventorymanagementsystem.database.ItemsDatabase
import com.example.inventorymanagementsystem.database.models.Sale
import com.example.inventorymanagementsystem.databinding.ActivitySaleDetailsBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SaleDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivitySaleDetailsBinding
    private val calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaleDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sale: Sale = ItemsDatabase.getInstance(this).getSalesDao()
            .getCertainSale(intent.getIntExtra("id", 1))

        binding.productNameEdit2.setText(sale.productName)
        binding.unitsEdit2.setText(sale.units.toString())
        binding.invoiceNumberEdit2.setText(sale.invoiceNo.toString())
        binding.dateEdit2.setText(sale.date)

        binding.dateEdit2.setOnClickListener {
            DatePickerDialog(
                binding.root.context,
                object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(
                        view: DatePicker?,
                        year: Int,
                        month: Int,
                        dayOfMonth: Int
                    ) {
                        calendar.set(year, month, dayOfMonth)
                        displayFormattedDate(calendar.timeInMillis)
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.doneEditing2.setOnClickListener {
            if (validateFields()) {
                val sale = Sale(
                    id = intent.getIntExtra("id", 1),
                    productName = binding.productNameEdit2.text.toString(),
                    date = binding.dateEdit2.text.toString(),
                    invoiceNo = binding.invoiceNumberEdit2.text.toString().toLong(),
                    units = binding.unitsEdit2.text.toString().toInt()
                )
                ItemsDatabase.getInstance(this).getSalesDao().updateSale(sale)
                Toast.makeText(
                    this@SaleDetailsActivity,
                    "Editing done Successfully!",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }

        binding.delete2.setOnClickListener {
            ItemsDatabase
                .getInstance(this)
                .getSalesDao()
                .deleteSale(sale)
            Toast.makeText(
                this@SaleDetailsActivity,
                "Deleted Successfully!",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }
    private fun displayFormattedDate(timestamp: Long) {
        binding.dateEdit2.setText(formatter.format(timestamp))
    }

    private fun validateFields(): Boolean {
        if (binding.productNameEdit2.text.isNullOrEmpty()) {
            binding.productNameEdit2.error = "Product Name is required"
            return false
        }
        if (binding.unitsEdit2.text.isNullOrEmpty() || binding.unitsEdit2.text.toString()
                .toInt() == 0 || binding.unitsEdit2.text.toString().toInt() < 0
        ) {
            binding.unitsEdit2.error = "Units is required"
            return false
        }
        if (binding.invoiceNumberEdit2.text.isNullOrEmpty()) {
            binding.invoiceNumberEdit2.error = "Invoice Number is required"
            return false
        }
        if (binding.dateEdit2.text.isNullOrEmpty()) {
            binding.dateEdit2.error = "Date is required"
            return false
        }
        return true
    }
}