package com.example.yeongpyo.androidStudyCoinHelper

import com.example.coinapi.CoinDB
import com.example.coinapi.OrderBookData
import com.example.coinapi.TickerData
import com.example.coinapi.TradesData
import com.example.yeongpyo.androidStudyCoinHelper.RxComm.APICallRX
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

    private fun getTrades() = observableSupport.tradesObservable.subscribe(getTradesData, getFail)

    private fun getTicker() = observableSupport.tickerObservable.subscribe(getTickerData, getFail)


    private val getFail : (Throwable) -> Unit ={}
    private val getTradesData : (TradesData) -> Unit = {view.setTrades(it.completeOrders) }
    private val getOrderBookData : (OrderBookData) -> Unit = { view.run { setAsk(it.ask); setBid(it.bid) }}
    private val getTickerData : (TickerData) -> Unit = { view.setTicker(it) }

    /*
    fun getFail(t: Throwable) {
        "ERR Print".LogPrint()
    }

    fun getTradesData(data: TradesData) {
        view.setTrades(data.completeOrders.toTypedArray())
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
