package com.example.coinapi

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIinterface {

    companion object {
        val retrofit = Retrofit.Builder()
        .baseUrl("https://api.coinone.co.kr")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    }

    @GET("/trades")
    fun getTrades(@Query("currency") coin: String): Observable<TredesData>


    @GET("/orderbook")
    fun getOrderBook(@Query("currency") coin: String): Observable<OrderBookData>

    @GET("/ticker")
    fun getTicker(@Query("currency") coin: String): Observable<TickerData>

}