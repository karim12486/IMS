package com.example.inventorymanagementsystem.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inventorymanagementsystem.database.models.Stock
import com.example.inventorymanagementsystem.databinding.ItemStockBinding

class StockAdapter(private var stockList: List<Stock>?) :
    RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    class StockViewHolder(val binding: ItemStockBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(stock: Stock) {
            binding.productName.text = stock.productName
            binding.units.text = "${stock.units}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStockBinding.inflate(inflater, parent, false)
        return StockViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return stockList?.size ?: 0
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val item = stockList?.get(position) ?: return
        holder.bind(item)
    }

    fun updateData(remainingStock: List<Stock>) {
        this.stockList = remainingStock
        notifyDataSetChanged()
    }
}