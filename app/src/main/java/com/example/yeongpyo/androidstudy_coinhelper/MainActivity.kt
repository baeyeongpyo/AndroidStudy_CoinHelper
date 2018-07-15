package com.example.yeongpyo.androidstudy_coinhelper

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.coinapi.APIinterface
import com.example.coinapi.OrderBookData
import com.example.coinapi.TickerData
import com.example.coinapi.TredesData
import com.example.yeongpyo.androidstudy_coinhelper.Adapter.CustomPagerAdater
import io.reactivex.Flowable
import io.reactivex.Observable
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
//            service.getTrades().enqueue(getCallBack<TredesData>())
        }

        APIinterface.retrofit.create(APIinterface::class.java)
                .getTrades()


/*
        Observable.just(
                service.getTrades().enqueue(getCallBack<TredesData>()),
                service.getOrderBook().enqueue(getCallBack<OrderBookData>()),
                service.getTicker().enqueue(getCallBack<TickerData>())

        )*/
    }

    fun <T>getCallBack() = object : Callback<T> {
        override fun onFailure(call: Call<T>?, t: Throwable?) {
            println("==============================================================================")
            println("====================== Err ===================")
            println("==============================================================================")
        }

        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            val data = (response as? Response<TredesData>)?.body()

            //fixme : Data Response <T> Type으로 넘기기

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
