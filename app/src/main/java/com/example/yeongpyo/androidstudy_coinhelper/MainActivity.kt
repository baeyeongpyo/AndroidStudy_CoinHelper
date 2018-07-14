package com.example.yeongpyo.androidstudy_coinhelper

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.yeongpyo.androidstudy_coinhelper.Adapter.CustomPagerAdater
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "ssss"

        vpContent.adapter = CustomPagerAdater(supportFragmentManager)

/*
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
    */

    }

}
