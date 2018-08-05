package com.example.yeongpyo.androidstudy_coinhelper

import android.util.Log
import com.example.coinapi.CoinDB
import com.example.coinapi.OrderBookData
import com.example.coinapi.TickerData
import com.example.coinapi.TredesData
import com.example.yeongpyo.androidstudy_coinhelper.RxComm.APICallRX
import io.reactivex.internal.operators.observable.ObservableInterval
import java.util.concurrent.TimeUnit

class CoinPresenter(val view :CoinContract.View) : CoinContract.Presenter {

    val ObservableSupport = APICallRX(CoinDB.BTC.coinName)

    override fun start() {
        RxIntervable.subscribe {
            getOrderBook()
            getTrades()
            getTicker()
        }
    }



    val RxIntervable = ObservableInterval.interval(2, 3, TimeUnit.SECONDS)

    private fun getOrderBook() = ObservableSupport.OrderBookObservable.subscribe(::getOrderBookData, ::getFail)

    private fun getTrades() = ObservableSupport.TredesObsevable.subscribe(::getTredesData, ::getFail)

    private fun getTicker() = ObservableSupport.TickerObservable.subscribe(::getTickerData, ::getFail)

    fun getFail(t: Throwable) {
        "ERR Print".LogPrint()
    }

    fun getTredesData(data: TredesData) {
        view.setTredes(data.completeOrders.toTypedArray())
    }

    fun getOrderBookData(data: OrderBookData) {
        view.setAsk(data.ask.toTypedArray())
        view.setBid(data.bid.toTypedArray())
    }

    fun getTickerData(data: TickerData) {
        view.setTicker(data)
        "Ticker OK".LogPrint()
    }

    private fun String.LogPrint() = Log.i("RetroFitTest", this)

}
