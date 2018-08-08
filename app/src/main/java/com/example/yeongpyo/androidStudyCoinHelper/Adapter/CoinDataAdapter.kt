package com.example.yeongpyo.androidStudyCoinHelper.Adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.example.coinapi.AskData
import com.example.coinapi.BidData
import com.example.coinapi.TradesCompleteOrders
import com.example.yeongpyo.androidStudyCoinHelper.BaseUtil.BaseRecyclerAdapter
import com.example.yeongpyo.androidStudyCoinHelper.BaseUtil.BaseRecyclerHolder
import com.example.yeongpyo.androidStudyCoinHelper.R

class CoinDataAdapter  {
    fun bidAdapterMaker() = object : BaseRecyclerAdapter<BidData>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
                object : BaseRecyclerHolder<BidData>(R.layout.item_data, parent) {
                    override fun onViewCreate(item: BidData?): Unit = with(itemView) {
                        findViewById<TextView>(R.id.Data1).text = item?.getprice()
                        findViewById<TextView>(R.id.Data2).text = item?.qty
                    }
                }
    }

    fun askAdapterMaker() = object : BaseRecyclerAdapter<AskData>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
                object : BaseRecyclerHolder<AskData>(R.layout.item_data, parent) {
                    override fun onViewCreate(item: AskData?): Unit = with(itemView) {
                        findViewById<TextView>(R.id.Data1).text = item?.getprice()
                        findViewById<TextView>(R.id.Data2).text = item?.qty
                    }
                }
    }

    fun tradesAdapterMaker() = object : BaseRecyclerAdapter<TradesCompleteOrders>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
                object : BaseRecyclerHolder<TradesCompleteOrders>(R.layout.item_subdata, parent) {
                    override fun onViewCreate(item: TradesCompleteOrders?): Unit = with(itemView) {
                        findViewById<TextView>(R.id.Data1).text = item?.getprice()
                        findViewById<TextView>(R.id.Data2).text = item?.qty
                    }
                }
    }
}