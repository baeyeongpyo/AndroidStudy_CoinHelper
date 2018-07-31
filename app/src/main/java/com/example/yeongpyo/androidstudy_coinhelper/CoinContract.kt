package com.example.yeongpyo.androidstudy_coinhelper

import com.example.coinapi.AskData
import com.example.coinapi.BidData
import com.example.coinapi.OrderBookData
import com.example.coinapi.TredesData
import com.example.yeongpyo.androidstudy_coinhelper.BaseUtil.BasePresenter
import com.example.yeongpyo.androidstudy_coinhelper.BaseUtil.BaseView


interface CoinContract {
    interface View : BaseView<Presenter> {
        fun setAsk(data : AskData)
        fun setBid(data : BidData)
        fun setOrderBook(data : OrderBookData)
        fun SetTredes(data : TredesData)

    }

    interface Presenter : BasePresenter {

    }
}