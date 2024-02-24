package com.example.inventorymanagementsystem.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.inventorymanagementsystem.adapters.StockAdapter
import com.example.inventorymanagementsystem.database.ItemsDatabase
import com.example.inventorymanagementsystem.database.models.Purchase
import com.example.inventorymanagementsystem.database.models.Sale
import com.example.inventorymanagementsystem.database.models.Stock
import com.example.inventorymanagementsystem.databinding.FragmentStockBinding

class StockFragment: Fragment() {

    lateinit var binding: FragmentStockBinding
    lateinit var adapter: StockAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStockBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = StockAdapter(null)
        binding.rvStock.adapter = adapter
        val list = ItemsDatabase.getInstance(requireContext()).getPurchasesDao().getPurchasesMinusSales()
        Log.e("XD", "onViewCreated: $list" )
        adapter.updateData(list)
    }
}