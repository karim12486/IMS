package com.example.inventorymanagementsystem.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.inventorymanagementsystem.database.models.Purchase
import com.example.inventorymanagementsystem.databinding.ItemPurchasesBinding

class PurchasesAdapter(private var purchasesList: List<Purchase>?, private val listener: OnPurchaseItemClickListener) :
    Adapter<PurchasesAdapter.PurchasesViewHolder>() {

    interface OnPurchaseItemClickListener {
        fun onPurchaseItemClick(purchase: Purchase)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchasesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPurchasesBinding.inflate(inflater, parent, false)
        return PurchasesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return purchasesList?.size ?: 0
    }

    override fun onBindViewHolder(holder: PurchasesViewHolder, position: Int) {
        val item = purchasesList?.get(position) ?: return
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener.onPurchaseItemClick(item)
        }
    }

    fun updateData(purchasesList: List<Purchase>?) {
        this.purchasesList = purchasesList
        notifyDataSetChanged()
    }

    class PurchasesViewHolder(val binding: ItemPurchasesBinding) : ViewHolder(binding.root) {
        fun bind(purchase: Purchase) {
            binding.productName.text = purchase.productName
            binding.date.text = "${purchase.date}"
            binding.invoiceNo.text = "${purchase.invoiceNo}"
            binding.units.text = "${purchase.units}"
        }
    }
}