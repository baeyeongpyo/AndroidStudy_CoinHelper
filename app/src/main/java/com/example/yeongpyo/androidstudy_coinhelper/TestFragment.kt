package com.example.yeongpyo.androidstudy_coinhelper

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class TestFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.testlayout, container, false)
        (activity as? MainActivity)?.supportActionBar?.let {
            it.title = " Coin list 1"
            it.setDisplayHomeAsUpEnabled(false)
        }
        return view
    }

}
