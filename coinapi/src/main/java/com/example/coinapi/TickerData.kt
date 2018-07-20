package com.example.coinapi

import android.graphics.Color

data class TickerData (
    val result: String,
    val volume: String,
    val last: String,
    val yesterday_last: String,
    val timestamp: String,
    val yesterday_low: String,
    val high: String,
    val currency: String,
    val low: String,
    val errorCode: String,
    val yesterday_first: String,
    val yesterday_volume: String,
    val yesterday_high: String,
    val first: String
)