package com.example.coinapi

import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIinterface : ObservableOnSubscribe<Any> {

    companion object {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.coinone.co.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @GET("/trades?currency={coin}")
    fun getTrades(coin : String): Observable<TredesData>

    @GET("/orderbook?currency={coin}")
    fun getOrderBook(coin : String): Observable<OrderBookData>

    @GET("/ticker?currency={coin}")
    fun getTicker(coin : String): Observable<TickerData>

}