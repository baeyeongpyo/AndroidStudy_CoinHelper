package com.example.yeongpyo.androidstudy_coinhelper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coinapi.APIinterface
import com.example.coinapi.TradesData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        thread {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.coinone.co.kr")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            val service = retrofit.create(APIinterface::class.java)
            service.getTrades().enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>?, t: Throwable?) {

                    println("==============================================================================")
                    println("====================== Err ===================")
                    println("==============================================================================")
                }

                override fun onResponse(call: Call<String>?, response: Response<String>?) {
                    val data = response?.body().toString()
                    println("==============================================================================")
                    println("====================== Sucess ===================")
                    println("==============================================================================")
                    println("data Start")
                    println(data)
                    println("data Final")
                }

            })
        }


    }
}
