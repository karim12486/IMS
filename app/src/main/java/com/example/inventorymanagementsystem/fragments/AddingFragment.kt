package com.example.inventorymanagementsystem.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import com.example.inventorymanagementsystem.databinding.FragmentAddingBinding
import java.text.SimpleDateFormat
import java.time.Year
import java.util.Calendar
import java.util.Locale


class AddingFragment : Fragment(), DatePickerDialog.OnDateSetListener {

    lateinit var binding: FragmentAddingBinding
    private val calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.date.showSoftInputOnFocus = false
        binding.date.setOnClickListener {
            DatePickerDialog(
                binding.root.context,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
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

}