package com.example.inventorymanagementsystem.fragments

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.app.NavUtils
import com.example.inventorymanagementsystem.database.ItemsDatabase
import com.example.inventorymanagementsystem.database.models.Purchase
import com.example.inventorymanagementsystem.databinding.ActivityPurchaseDetailsBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class PurchaseDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityPurchaseDetailsBinding
    private val calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val purchase: Purchase = ItemsDatabase.getInstance(this).getPurchasesDao()
            .getCertainPurchase(intent.getIntExtra("id", 1))
        binding.productNameEdit.setText(purchase.productName)
        binding.unitsEdit.setText(purchase.units.toString())
        binding.invoiceNumberEdit.setText(purchase.invoiceNo.toString())
        binding.dateEdit.setText(purchase.date)

        binding.dateEdit.setOnClickListener {
            DatePickerDialog(
                binding.root.context,
                object : OnDateSetListener {
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

        binding.doneEditing.setOnClickListener {
            if (validateFields()) {
                val purchase = Purchase(
                    id = intent.getIntExtra("id", 1),
                    productName = binding.productNameEdit.text.toString(),
                    date = binding.dateEdit.text.toString(),
                    invoiceNo = binding.invoiceNumberEdit.text.toString().toLong(),
                    units = binding.unitsEdit.text.toString().toInt()
                )
                ItemsDatabase
                    .getInstance(this)
                    .getPurchasesDao()
                    .updatePurchase(purchase)
                Toast.makeText(
                    this@PurchaseDetailsActivity,
                    "Editing done Successfully!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            finish()
//            Log.e("xd", "onCreate: button pressed")
        }

        binding.delete.setOnClickListener {
            ItemsDatabase
                .getInstance(this)
                .getPurchasesDao()
                .deletePurchase(purchase)
            Toast.makeText(
                this@PurchaseDetailsActivity,
                "Deleted Successfully!",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }

//    fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
//        calendar.set(year, month, dayOfMonth)
//        displayFormattedDate(calendar.timeInMillis)
//    }

    private fun displayFormattedDate(timestamp: Long) {
        binding.dateEdit.setText(formatter.format(timestamp))
    }

    private fun validateFields(): Boolean {
        if (binding.productNameEdit.text.isNullOrEmpty()) {
            binding.productNameEdit.error = "Product Name is required"
            return false
        }
        if (binding.unitsEdit.text.isNullOrEmpty() || binding.unitsEdit.text.toString()
                .toInt() == 0 || binding.unitsEdit.text.toString().toInt() < 0
        ) {
            binding.unitsEdit.error = "Units is required"
            return false
        }
        if (binding.invoiceNumberEdit.text.isNullOrEmpty()) {
            binding.invoiceNumberEdit.error = "Invoice Number is required"
            return false
        }
        if (binding.dateEdit.text.isNullOrEmpty()) {
            binding.dateEdit.error = "Date is required"
            return false
        }
        return true
    }
}