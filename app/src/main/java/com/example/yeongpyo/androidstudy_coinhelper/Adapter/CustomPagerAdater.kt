package com.example.yeongpyo.androidstudy_coinhelper.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.yeongpyo.androidstudy_coinhelper.Coin1_Fragment
import com.example.yeongpyo.androidstudy_coinhelper.testFragment

class CustomPagerAdater(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    val fraglist = arrayOf(
            Coin1_Fragment()
            , testFragment()
    )
    override fun getItem(position: Int): Fragment = fraglist[position]
    override fun getCount(): Int = 2
}