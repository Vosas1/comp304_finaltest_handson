package com.victor.osaikhuwuomwan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.victor.osaikhuwuomwan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CompanyStockViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Room Database
        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "company_stock_db"
        ).build()

        // Initialize Repository
        val repository = CompanyStockRepository(database.companyStockDao())

        // Initialize ViewModel
        val viewModelFactory = CompanyStockViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CompanyStockViewModel::class.java)

        // Set up click listeners
        setupClickListeners()

        // Observe LiveData for changes
        observeLiveData()
    }

    private fun setupClickListeners() {
        binding.btnInsert.setOnClickListener {
            val stocks = listOf(
                CompanyStock("Google Inc.", 2800.0, 2850.0),
                CompanyStock("Amazon Inc.", 3300.0, 3350.0),
                CompanyStock("Samsung Electronics", 1400.0, 1450.0)
            )
            stocks.forEach { stock ->
                viewModel.insert(stock)
            }
        }

        binding.btnDisplay.setOnClickListener {
            val selectedStock = when (binding.radioGroupStocks.checkedRadioButtonId) {
                R.id.radioGOOGL -> "Google Inc."
                R.id.radioAMZN -> "Amazon Inc."
                R.id.radioSSNLF -> "Samsung Electronics"
                else -> ""
            }

            if (selectedStock.isNotEmpty()) {
                viewModel.fetchCompanyStockByName(selectedStock)
            } else {
                binding.textViewStockInfo.text = "Please select a stock"
            }
        }
    }

    private fun observeLiveData() {
        viewModel.selectedStock.observe(this, Observer { stock ->
            stock?.let {
                val stockInfo = "Company: ${it.companyName}, Opening Price: ${it.openingPrice}, Closing Price: ${it.closingPrice}"
                binding.textViewStockInfo.text = stockInfo
            } ?: run {
                binding.textViewStockInfo.text = "Stock not found"
            }
        })
    }
}

