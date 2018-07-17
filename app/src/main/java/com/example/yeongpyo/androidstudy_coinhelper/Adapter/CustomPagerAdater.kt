package com.example.yeongpyo.androidstudy_coinhelper.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import com.example.yeongpyo.androidstudy_coinhelper.Coin1Fragment
import com.example.yeongpyo.androidstudy_coinhelper.TestFragment

class CustomPagerAdater(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val fraglist = arrayOf(
            Coin1Fragment()
            , TestFragment()
    )

    override fun getPageTitle(position: Int): CharSequence? {

        return super.getPageTitle(position)
    }

    override fun getItem(position: Int): Fragment = fraglist[position]
    override fun getCount(): Int = fraglist.size
}