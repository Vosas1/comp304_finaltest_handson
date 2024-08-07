package com.victor.osaikhuwuomwan

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class StockBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val stockInfo = intent.getStringExtra("stock_info")
        Toast.makeText(context, "Stock Info: $stockInfo", Toast.LENGTH_LONG).show()
    }
}