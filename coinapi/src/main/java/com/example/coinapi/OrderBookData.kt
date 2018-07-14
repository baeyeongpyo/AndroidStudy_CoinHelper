package com.example.coinapi

data class OrderBookData (
        val timestamp: String,
        val bid: List<BidData>,
        val errorCode: String,
        val currency: String,
        val result: String,
        val ask: List<AskData>
)
data class AskData(
        val price: String,
        val qty: String
)

data class BidData(
        val price: String,
        val qty: String
)
