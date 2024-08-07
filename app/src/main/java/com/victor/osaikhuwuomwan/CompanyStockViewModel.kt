package com.victor.osaikhuwuomwan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompanyStockViewModel(private val repository: CompanyStockRepository) : ViewModel() {

    private val _selectedStock = MutableLiveData<CompanyStock?>()
    val selectedStock: LiveData<CompanyStock?> get() = _selectedStock

    fun insert(companyStock: CompanyStock) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(companyStock)
        }
    }

    fun fetchCompanyStockByName(companyName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val stock = repository.getCompanyStockByName(companyName)
            _selectedStock.postValue(stock)
        }
    }
}

class CompanyStockViewModelFactory(private val repository: CompanyStockRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CompanyStockViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CompanyStockViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
