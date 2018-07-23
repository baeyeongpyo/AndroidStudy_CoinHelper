package com.example.yeongpyo.androidstudy_coinhelper.Adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.example.coinapi.APIDecimalSupport
import com.example.coinapi.AskData
import com.example.coinapi.BidData
import com.example.coinapi.TredesCompleteOrders
import com.example.yeongpyo.androidstudy_coinhelper.BaseUtil.BaseRecyclerAdapter
import com.example.yeongpyo.androidstudy_coinhelper.BaseUtil.BaseRecyclerHolder
import com.example.yeongpyo.androidstudy_coinhelper.R

class CoinDataAdapter : APIDecimalSupport() {
    fun BidAdapterMaker(listview: RecyclerView) = object : BaseRecyclerAdapter<BidData>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
                object : BaseRecyclerHolder<BidData>(R.layout.item_data, listview) {
                    override fun onViewCreate(item: BidData?): Unit = with(itemView) {
                        findViewById<TextView>(R.id.Data1).text = item?.price?.comma()
                        findViewById<TextView>(R.id.Data2).text = item?.qty
                    }
                }
    }

    fun AskAdapterMaker(listview: RecyclerView) = object : BaseRecyclerAdapter<AskData>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
                object : BaseRecyclerHolder<AskData>(R.layout.item_data, listview) {
                    override fun onViewCreate(item: AskData?): Unit = with(itemView) {
                        findViewById<TextView>(R.id.Data1).text = item?.qty
                        findViewById<TextView>(R.id.Data2).text = item?.price?.comma()
                    }
                }
    }

    fun TredesAdapterMaker(listview: RecyclerView) = object : BaseRecyclerAdapter<TredesCompleteOrders>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
                object : BaseRecyclerHolder<TredesCompleteOrders>(R.layout.item_subdata, listview) {
                    override fun onViewCreate(item: TredesCompleteOrders?): Unit = with(itemView) {
                        findViewById<TextView>(R.id.Data1).text = item?.price?.comma()
                        findViewById<TextView>(R.id.Data2).text = item?.qty
                    }
                }
    }
}