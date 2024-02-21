package com.example.inventorymanagementsystem.fragments

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import com.example.inventorymanagementsystem.R
import com.example.inventorymanagementsystem.database.ItemsDatabase
import com.example.inventorymanagementsystem.database.models.Purchase
import com.example.inventorymanagementsystem.databinding.ActivityDetailsBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DetailsActivity : AppCompatActivity(){
    lateinit var binding: ActivityDetailsBinding
    private val calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_details)

        val purchase: Purchase = ItemsDatabase.getInstance(this).getPurchasesDao().getCertainPurchase(intent.getIntExtra("id", 1))
        binding.productNameEdit.setText(purchase.productName)
        binding.dateEdit.setText(purchase.date)

        val context = this@DetailsActivity

//        binding.dateEdit.setOnClickListener {
//            DatePickerDialog(
//                context,
//                this,
//                calendar.get(Calendar.YEAR),
//                calendar.get(Calendar.MONTH),
//                calendar.get(Calendar.DAY_OF_MONTH)
//            ).show()
//        }
        binding.doneEditing.setOnClickListener {
//            if (true) {
//                val purchase = Purchase(
//                    productName = binding.productNameEdit.text.toString(),
//                    date = binding.dateEdit.text.toString(),
//                    invoiceNo = binding.invoiceNumberEdit.text.toString().toLong(),
//                    units = binding.unitsEdit.text.toString().toInt()
//                )
//                ItemsDatabase
//                    .getInstance(this)
//                    .getPurchasesDao()
//                    .updatePurchase(purchase)
//                Toast.makeText(this@DetailsActivity, "Editing done Successfully!", Toast.LENGTH_SHORT).show()
//            }
            val toast = Toast.makeText(this@DetailsActivity, "Editing not done Successfully!", Toast.LENGTH_SHORT).show()
        }
    }

//    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
//        calendar.set(year, month, dayOfMonth)
//        displayFormattedDate(calendar.timeInMillis)
//    }
//
//    private fun displayFormattedDate(timestamp: Long) {
//        binding.dateEdit.setText(formatter.format(timestamp))
//    }

    private fun validateFields(): Boolean {
        if (binding.productNameEdit.text.isNullOrEmpty()) {
            binding.productNameEdit.error = "Product Name is required"
            return false
        }
        if (binding.unitsEdit.text.isNullOrEmpty() || binding.unitsEdit.text.toString().toInt() == 0 || binding.unitsEdit.text.toString().toInt() < 0) {
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