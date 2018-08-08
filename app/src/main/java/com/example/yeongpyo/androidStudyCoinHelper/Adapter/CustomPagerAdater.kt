package com.example.yeongpyo.androidStudyCoinHelper.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.yeongpyo.androidStudyCoinHelper.Coin1Fragment

class CustomPagerAdater(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val fragList = arrayOf(Coin1Fragment())

    override fun getItem(position: Int): Fragment = fragList[position]
    override fun getCount(): Int = fragList.size
}