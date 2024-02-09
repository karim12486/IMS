package com.example.inventorymanagementsystem.fragments

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import com.example.inventorymanagementsystem.database.PurchasesDatabase
import com.example.inventorymanagementsystem.database.SalesDatabase
import com.example.inventorymanagementsystem.database.models.Purchase
import com.example.inventorymanagementsystem.database.models.Sale
import com.example.inventorymanagementsystem.databinding.FragmentAddingBinding
import java.text.SimpleDateFormat
import java.time.Year
import java.util.Calendar
import java.util.Locale


class AddingFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    lateinit var binding: FragmentAddingBinding
    private val calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddingBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.date.setOnClickListener {
            DatePickerDialog(
                binding.root.context,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        binding.purchases.setOnClickListener {
            if (validateFields()) {
                val purchase = Purchase(
                    productName = binding.productName.text.toString(),
                    date = binding.date.text.toString(),
                    invoiceNo = binding.invoiceNumber.text.toString().toLong(),
                    units = binding.units.text.toString().toInt()
                )
                PurchasesDatabase
                    .getInstance(requireContext())
                    .getPurchasesDao()
                    .insertPurchase(purchase)
            }
        }

        binding.sales.setOnClickListener {
            if (validateFields()){
                val sale = Sale(
                    productName = binding.productName.text.toString(),
                    date = binding.date.text.toString(),
                    invoiceNo = binding.invoiceNumber.text.toString().toLong(),
                    units = binding.units.text.toString().toInt()
                )
                SalesDatabase
                    .getInstance(requireContext())
                    .getSalesDao()
                    .insertSale(sale)
            }
        }
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Log.e("calendar", "$year -- $month -- $dayOfMonth")
        calendar.set(year, month, dayOfMonth)
        displayFormattedDate(calendar.timeInMillis)
    }

    private fun displayFormattedDate(timestamp: Long) {
        binding.date.setText(formatter.format(timestamp))
    }

    private fun validateFields(): Boolean {
        if (binding.productName.text.isNullOrEmpty()) {
            binding.productName.error = "Product Name is required"
            return false
        }
        if (binding.units.text.isNullOrEmpty() || binding.units.text.toString().toInt() == 0 || binding.units.text.toString().toInt() < 0) {
            binding.units.error = "Units is required"
            return false
        }
        if (binding.invoiceNumber.text.isNullOrEmpty()) {
            binding.invoiceNumber.error = "Invoice Number is required"
            return false
        }
        if (binding.date.text.isNullOrEmpty()) {
            binding.date.error = "Date is required"
            return false
        }
        return true
    }
}