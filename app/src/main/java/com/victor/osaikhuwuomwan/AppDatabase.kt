package com.victor.osaikhuwuomwan

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CompanyStock::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun companyStockDao(): CompanyStockDao
}
