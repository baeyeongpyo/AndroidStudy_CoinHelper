package com.example.yeongpyo.androidstudy_coinhelper

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.coinapi.*
import com.example.yeongpyo.androidstudy_coinhelper.Adapter.CustomPagerAdater
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
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

        APIinterface.retrofit.create(APIinterface::class.java)
                .getTrades(CoinDB.BTC.name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(::getTredesData, ::getFail)


    }
}
fun getFail( t : Throwable ){}
fun getTredesData(data : TredesData) = data.completeOrders.forEach { println(it) }


