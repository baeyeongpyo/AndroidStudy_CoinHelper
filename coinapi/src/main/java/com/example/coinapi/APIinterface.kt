package com.example.coinapi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIinterface {

    companion object {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.coinone.co.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @GET("/trades")
    fun getTrades(): Call<TredesData>

    @GET("/orderbook")
    fun getOrderBook(): Call<OrderBookData>

    @GET("/ticker")
    fun getTicker(): Call<TickerData>

}