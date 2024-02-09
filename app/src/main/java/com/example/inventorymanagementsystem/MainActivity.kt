package com.example.inventorymanagementsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.inventorymanagementsystem.databinding.ActivityMainBinding
import com.example.inventorymanagementsystem.fragments.AddingFragment
import com.example.inventorymanagementsystem.fragments.PurchasesFragment
import com.example.inventorymanagementsystem.fragments.SalesFragment
import com.example.inventorymanagementsystem.fragments.StockFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_stock -> {
                    pushFragment(StockFragment())
                }

                R.id.navigation_adding -> {
                    pushFragment(AddingFragment())
                }

                R.id.navigation_purchases -> {
                    pushFragment(PurchasesFragment())
                }

                R.id.navigation_sales -> {
                    pushFragment(SalesFragment())
                }
            }
            return@setOnItemSelectedListener true
        }
        binding.bottomNavBar.selectedItemId = R.id.navigation_stock
    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.imsFragmentContainer.id, fragment)
            .commit()
    }
}