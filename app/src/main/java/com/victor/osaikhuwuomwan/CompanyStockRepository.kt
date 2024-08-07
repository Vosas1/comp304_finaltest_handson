package com.victor.osaikhuwuomwan

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CompanyStockRepository(private val companyStockDao: CompanyStockDao) {

    suspend fun insert(companyStock: CompanyStock) {
        withContext(Dispatchers.IO) {
            companyStockDao.insertCompanyStock(companyStock)
        }
    }

    suspend fun getCompanyStockByName(companyName: String): CompanyStock? {
        return withContext(Dispatchers.IO) {
            companyStockDao.getCompanyStockByName(companyName)
        }
    }
}


