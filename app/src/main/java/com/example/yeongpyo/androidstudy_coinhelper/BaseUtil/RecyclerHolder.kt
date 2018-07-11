package com.example.yeongpyo.androidstudy_coinhelper.BaseUtil

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class RecyclerHolder<ITEM : Any>(layout: Int, parent: ViewGroup?) :
        RecyclerView.ViewHolder(LayoutInflater.from(parent?.context).inflate(layout, parent, false)) {

    fun onBindVIewHolder(item: Any?) = try {
        @Suppress("UNCHECKED_CAST")
        onViewCreate(item as? ITEM?)
    } catch (e: Exception) {
        itemView.visibility = View.GONE
    }

    abstract fun onViewCreate(item: ITEM?)

}