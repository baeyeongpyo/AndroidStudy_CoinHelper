package com.example.coinapi

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIinterface {

    companion object {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.coinone.co.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @GET("/trades")
    fun getTrades(): Observable<TredesData>

    @GET("/orderbook")
    fun getOrderBook(): Observable<OrderBookData>

    @GET("/ticker")
    fun getTicker(): Observable<TickerData>

}