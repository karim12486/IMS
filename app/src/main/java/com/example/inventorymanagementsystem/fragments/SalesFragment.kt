package com.example.inventorymanagementsystem.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.inventorymanagementsystem.adapters.PurchasesAdapter
import com.example.inventorymanagementsystem.adapters.SalesAdapter
import com.example.inventorymanagementsystem.database.ItemsDatabase
import com.example.inventorymanagementsystem.database.models.Purchase
import com.example.inventorymanagementsystem.database.models.Sale
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
        adapter = SalesAdapter(null, object : SalesAdapter.OnSaleItemClickListener{
            override fun onSaleItemClick(sale: Sale) {
                val intent = Intent(this@SalesFragment.context, SaleDetailsActivity::class.java)
                intent.putExtra("id", sale.id)
                startActivity(intent)
            }
        })
        binding.rvSales.adapter = adapter
        val list = ItemsDatabase.getInstance(requireContext()).getSalesDao().getAllSales().sortedByDescending { it.date }
        val searchEditText = binding.searchbar
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable) {
                if (s.isNullOrEmpty()){
                    val list = ItemsDatabase.getInstance(requireContext()).getSalesDao().getAllSales().sortedByDescending { it.date }
                    adapter.updateData(list)
                }else {
                    val searchQuery = s.toString()
                    val results = ItemsDatabase.getInstance(requireContext()).getSalesDao()
                        .searchSales(searchQuery)
                    adapter.updateData(results)
                }
            }

        })
        adapter.updateData(list)
    }
    override fun onResume() {
        super.onResume()
        val list = ItemsDatabase.getInstance(requireContext()).getSalesDao().getAllSales().sortedByDescending { it.date }
        adapter.updateData(list)
    }
}