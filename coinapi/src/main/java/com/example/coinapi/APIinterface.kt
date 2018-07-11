package com.example.coinapi

import retrofit2.Call
import retrofit2.http.GET

interface APIinterface{

    @GET("/trades")
    fun getTrades() : Call<TradesData>

}