package com.example.yeongpyo.androidstudy_coinhelper

import com.example.coinapi.*
import com.example.yeongpyo.androidstudy_coinhelper.BaseUtil.BasePresenter
import com.example.yeongpyo.androidstudy_coinhelper.BaseUtil.BaseView


interface CoinContract {

    interface View : BaseView<Presenter> {
        fun setAsk(data : Array<AskData>)
        fun setBid(data : Array<BidData>)
        fun setTredes(data : Array<TredesCompleteOrders>)
        fun setTicker(data: TickerData)
    }

    interface Presenter : BasePresenter {

    }
}