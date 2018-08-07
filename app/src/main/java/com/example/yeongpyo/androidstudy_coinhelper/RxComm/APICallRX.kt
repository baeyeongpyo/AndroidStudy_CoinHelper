package com.example.yeongpyo.androidstudy_coinhelper.RxComm

import com.example.coinapi.APIinterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class APICallRX(APIParsingName : String) {

    val api = APIinterface.retrofit.create(APIinterface::class.java)

    fun <T> Observable<T>.returnObservable() : Observable<T> = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    val tickerObservable = api.getTicker(APIParsingName).returnObservable()

    val tradesObservable = api.getTrades(APIParsingName).returnObservable()

    val orderBookObservable = api.getOrderBook(APIParsingName).returnObservable()

}