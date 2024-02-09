package com.example.inventorymanagementsystem.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.inventorymanagementsystem.adapters.SalesAdapter
import com.example.inventorymanagementsystem.database.SalesDatabase
import com.example.inventorymanagementsystem.databinding.FragmentSalesBinding

class SalesFragment: Fragment() {

lateinit var binding: FragmentSalesBinding
lateinit var adapter: SalesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSalesBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SalesAdapter(null)
        binding.rvSales.adapter = adapter
        val list = SalesDatabase.getInstance(requireContext()).getSalesDao().getAllSales().sortedByDescending { it.date }
        adapter.updateData(list)
    }
}