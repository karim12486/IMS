package com.example.inventorymanagementsystem.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.inventorymanagementsystem.database.models.Sale
import com.example.inventorymanagementsystem.databinding.ItemSalesBinding

class SalesAdapter(private var salesList: List<Sale>?) :
    Adapter<SalesAdapter.SalesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalesViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSalesBinding.inflate(inflater)
        return SalesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return salesList?.size ?: 0
    }

    override fun onBindViewHolder(holder: SalesViewHolder, position: Int) {
        val item = salesList?.get(position) ?: return
        holder.bind(item)
    }

    fun updateData(salesList: List<Sale>?) {
        this.salesList = salesList
        notifyDataSetChanged()
    }

    class SalesViewHolder(val binding: ItemSalesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sale: Sale) {
            binding.productName.text = sale.productName
            binding.units.text = "${sale.units}"
            binding.invoiceNo.text = "${sale.invoiceNo}"
            binding.date.text = "${sale.date}"
        }
    }
}