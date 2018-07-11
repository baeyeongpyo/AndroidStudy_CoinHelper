package com.example.yeongpyo.androidstudy_coinhelper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.coinapi.APIinterface
import com.example.coinapi.TradesData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.coinone.co.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val data = retrofit.create(APIinterface::class.java).getTrades()
        data.enqueue(object : Callback<TradesData> {
            override fun onFailure(call: Call<TradesData>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<TradesData>?, response: Response<TradesData>?) {
                val data1 = response
            }

        })




    }
}
