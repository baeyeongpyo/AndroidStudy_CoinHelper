package com.example.yeongpyo.androidstudy_coinhelper

import android.annotation.SuppressLint
import android.app.PendingIntent.getActivity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.FragmentActivity
import com.example.coinapi.APIinterface
import com.example.coinapi.TradesData
import com.example.yeongpyo.androidstudy_coinhelper.Adapter.CustomPagerAdater
import kotlinx.android.synthetic.main.activity_main.*
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

        supportActionBar?.setTitle("ssss")

        fragmentlayout.adapter = CustomPagerAdater(supportFragmentManager)

        thread {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.coinone.co.kr")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            val service = retrofit.create(APIinterface::class.java)
            service.getTrades().enqueue(object : Callback<TradesData> {
                override fun onFailure(call: Call<TradesData>?, t: Throwable?) {

                    println("==============================================================================")
                    println("====================== Err ===================")
                    println("==============================================================================")
                }

                override fun onResponse(call: Call<TradesData>?, response: Response<TradesData>?) {
                    val data = response?.body().toString()
                    println("==============================================================================")
                    println("====================== Sucess ===================")
                    println("==============================================================================")
                    println("data Start")
                    response?.body()?.completeOrders?.forEach {
                        println("${it.isAsk} + ${it.price} + ${it.qty} + ${it.timestamp}")

                    }
                    println()
                    println("data Final")
                }

            })
        }

    }

}
