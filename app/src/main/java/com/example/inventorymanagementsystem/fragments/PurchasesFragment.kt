package com.example.inventorymanagementsystem.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.inventorymanagementsystem.adapters.PurchasesAdapter
import com.example.inventorymanagementsystem.database.ItemsDatabase
import com.example.inventorymanagementsystem.databinding.FragmentPurchasesBinding

class PurchasesFragment: Fragment() {

lateinit var binding: FragmentPurchasesBinding
lateinit var adapter: PurchasesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPurchasesBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PurchasesAdapter(null)
        binding.rvPurchases.adapter = adapter
        val list = ItemsDatabase.getInstance(requireContext()).getPurchasesDao().getAllPurchases().sortedByDescending { it.date }
        adapter.updateData(list)
    }
}