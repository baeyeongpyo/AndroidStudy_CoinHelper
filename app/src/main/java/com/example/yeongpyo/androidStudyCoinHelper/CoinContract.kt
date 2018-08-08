package com.example.yeongpyo.androidStudyCoinHelper

import com.example.coinapi.*
import com.example.yeongpyo.androidStudyCoinHelper.BaseUtil.BasePresenter
import com.example.yeongpyo.androidStudyCoinHelper.BaseUtil.BaseView


interface CoinContract {

    interface View : BaseView<Presenter> {
        fun setAsk(data : List<AskData>)
        fun setBid(data : List<BidData>)
        fun setTrades(data : List<TradesCompleteOrders>)
        fun setTicker(data: TickerData)
    }

    interface Presenter : BasePresenter
}