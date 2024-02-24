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
import com.example.inventorymanagementsystem.database.ItemsDatabase
import com.example.inventorymanagementsystem.database.models.Purchase
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
        adapter = PurchasesAdapter(null, object : PurchasesAdapter.OnPurchaseItemClickListener{
            override fun onPurchaseItemClick(purchase: Purchase) {
                val intent = Intent(this@PurchasesFragment.context, PurchaseDetailsActivity::class.java)
                intent.putExtra("id", purchase.id)
                startActivity(intent)
            }
        })
        binding.rvPurchases.adapter = adapter
        val list = ItemsDatabase.getInstance(requireContext()).getPurchasesDao().getAllPurchases().sortedByDescending { it.date }
        val searchEditText = binding.searchbar
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable) {
                if (s.isNullOrEmpty()){
                    val list = ItemsDatabase.getInstance(requireContext()).getPurchasesDao().getAllPurchases().sortedByDescending { it.date }
                    adapter.updateData(list)
                }else {
                    val searchQuery = s.toString()
                    val results = ItemsDatabase.getInstance(requireContext()).getPurchasesDao()
                        .searchPurchases(searchQuery)
                    adapter.updateData(results)
                }
            }

        })
        adapter.updateData(list)
    }
    override fun onResume() {
        super.onResume()
        val list = ItemsDatabase.getInstance(requireContext()).getPurchasesDao().getAllPurchases().sortedByDescending { it.date }
        adapter.updateData(list)
    }
}