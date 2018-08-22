package com.example.yeongpyo.androidStudyCoinHelper

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import com.example.coinapi.*
import com.example.yeongpyo.androidStudyCoinHelper.RxComm.APICallRX
import io.reactivex.internal.operators.observable.ObservableInterval
import java.util.concurrent.TimeUnit

class CoinViewModel {

    val currentPrice = ObservableField<String>()
    val previousDay = ObservableField<String>()
    val dayBefore = ObservableField<String>()
    val highPrice = ObservableField<String>()
    val lowPrice = ObservableField<String>()
    val volumeData = ObservableField<String>()

    val tradesList = ObservableArrayList<TradesCompleteOrders>()
    val askList = ObservableArrayList<AskData>()
    val bidList = ObservableArrayList<BidData>()

    private val observableSupport = APICallRX(CoinDB.BTC.coinName)

    private val rxInterval = ObservableInterval.interval(2, 3, TimeUnit.SECONDS)

    init{rxInterval.subscribe {
            getOrderBook()
            getTrades()
            getTicker()
        }
    }

    private fun getOrderBook() = observableSupport.orderBookObservable.subscribe(getOrderBookData, getFail)

    private fun getTrades() = observableSupport.tradesObservable.subscribe(getTradesData, getFail)

    private fun getTicker() = observableSupport.tickerObservable.subscribe(getTickerData, getFail)


    private val getFail : (Throwable) -> Unit ={}
    private val getTradesData : (TradesData) -> Unit = {tradesList.addAll(it.completeOrders) }
    private val getOrderBookData : (OrderBookData) -> Unit = { askList.addAll(it.ask); bidList.addAll(it.bid) }
    private val getTickerData : (TickerData) -> Unit = { data ->
        currentPrice.set(data.getCurrentPrinceValue())
        previousDay.set(data.getPreviousDayValue())
        dayBefore.set(data.getDayBeforeValue())
        highPrice.set(data.getHighPriceValue())
        lowPrice.set(data.getLowPriceValue())
        volumeData.set(data.getVolumeValue())
    }
}