package com.example.yeongpyo.androidstudy_coinhelper.BaseUtil

import android.support.v7.widget.RecyclerView

abstract class BaseRecyclerAdapter<ITEM : Any> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data = mutableListOf<ITEM>()

     override fun getItemCount(): Int= data.size

     override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
         (holder as? BaseRecyclerHolder<*>)?.onBindVIewHolder(data[position])
     }


 }