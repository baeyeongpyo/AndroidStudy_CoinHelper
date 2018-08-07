package com.example.yeongpyo.androidstudy_coinhelper

import com.example.coinapi.CoinDB
import com.example.coinapi.OrderBookData
import com.example.coinapi.TickerData
import com.example.coinapi.TredesData
import com.example.yeongpyo.androidstudy_coinhelper.RxComm.APICallRX
import io.reactivex.internal.operators.observable.ObservableInterval
import java.util.concurrent.TimeUnit

class CoinPresenter(val view :CoinContract.View) : CoinContract.Presenter {

    private val observableSupport = APICallRX(CoinDB.BTC.coinName)

    override fun start() {
        rxInterval.subscribe {
            getOrderBook()
            getTrades()
            getTicker()
        }
    }



    private val rxInterval = ObservableInterval.interval(2, 3, TimeUnit.SECONDS)

    private fun getOrderBook() = observableSupport.orderBookObservable.subscribe(getOrderBookData, getFail)

    private fun getTrades() = observableSupport.tradesObservable.subscribe(getTredesData, getFail)

    private fun getTicker() = observableSupport.tickerObservable.subscribe(getTickerData, getFail)


    private val getFail : (Throwable) -> Unit ={}
    private val getTredesData : (TredesData) -> Unit = {view.setTredes(it.completeOrders.toTypedArray()) }
    private val getOrderBookData : (OrderBookData) -> Unit = { view.run { setAsk(it.ask.toTypedArray()); setBid(it.bid.toTypedArray()) }}
    private val getTickerData : (TickerData) -> Unit = { view.setTicker(it) }

    /*
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
    */



}
