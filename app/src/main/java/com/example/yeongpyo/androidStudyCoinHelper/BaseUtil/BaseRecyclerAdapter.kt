package com.example.yeongpyo.androidStudyCoinHelper.BaseUtil

import android.support.v7.widget.RecyclerView

abstract class BaseRecyclerAdapter<ITEM : Any> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data = mutableListOf<ITEM>()

    fun addData(ItemData : List<ITEM>) {
//        ItemData.forEach { data.add(it) }
        data.addAll(ItemData)
        notifyDataSetChanged()
    }

     override fun getItemCount(): Int= data.size

     override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
         (holder as? BaseRecyclerHolder<*>)?.onBindVIewHolder(data[position])
     }


 }