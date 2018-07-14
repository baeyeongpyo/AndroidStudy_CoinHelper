package com.example.yeongpyo.androidstudy_coinhelper

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.coinapi.APIinterface
import com.example.coinapi.TredesData
import com.example.yeongpyo.androidstudy_coinhelper.Adapter.CustomPagerAdater
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "ssss"

        vpContent.adapter = CustomPagerAdater(supportFragmentManager)


        val service = APIinterface.retrofit.create(APIinterface::class.java)
        thread {
            service.getTrades().enqueue(getCallBack<TredesData>())
        }


    }

    fun <T>getCallBack() = object : Callback<T> {
        override fun onFailure(call: Call<T>?, t: Throwable?) {
            println("==============================================================================")
            println("====================== Err ===================")
            println("==============================================================================")
        }

        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            val data = (response as? Response<TredesData>)?.body()
            println("==============================================================================")
            println("====================== Sucess ===================")
            println("==============================================================================")
            println("data Start")
            data?.completeOrders?.forEach {
                println(it)
            }
        }

    }

}
