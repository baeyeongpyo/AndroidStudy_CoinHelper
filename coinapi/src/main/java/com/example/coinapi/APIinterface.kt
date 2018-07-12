package com.example.coinapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIinterface {

    @GET("/trades")
    fun getTrades(): Call<String>

}