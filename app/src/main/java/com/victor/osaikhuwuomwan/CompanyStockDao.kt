package com.victor.osaikhuwuomwan

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CompanyStockDao {

    @Insert
    fun insertCompanyStock(vararg companyStocks: CompanyStock)

    @Query("SELECT * FROM company_stock WHERE companyName = :companyName")
    fun getCompanyStockByName(companyName: String): CompanyStock

    @Query("SELECT * FROM company_stock")
    fun getAllCompanyStocks(): List<CompanyStock>
}
