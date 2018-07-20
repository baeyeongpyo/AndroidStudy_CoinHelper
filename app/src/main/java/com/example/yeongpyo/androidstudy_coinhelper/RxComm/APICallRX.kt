package com.example.yeongpyo.androidstudy_coinhelper.RxComm

import com.example.coinapi.APIinterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class APICallRX(APIParsingName : String) {
    val TickerObservable = APIinterface.retrofit.create(APIinterface::class.java)
    .getTicker(APIParsingName)
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())

    val TredesObsevable = APIinterface.retrofit.create(APIinterface::class.java)
    .getTrades(APIParsingName)
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())

    val OrderBookObservable = APIinterface.retrofit.create(APIinterface::class.java)
    .getOrderBook(APIParsingName)
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())

}