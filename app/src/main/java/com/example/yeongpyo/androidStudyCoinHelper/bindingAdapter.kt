package com.example.yeongpyo.androidStudyCoinHelper

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.example.yeongpyo.androidStudyCoinHelper.BaseUtil.BaseRecyclerAdapter

@BindingAdapter("bind:adapterItem")
fun bindingAdapterItem(rv_list : RecyclerView, item : List<Nothing>){
    (rv_list.adapter as BaseRecyclerAdapter<*>).addData(item)
}