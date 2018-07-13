package com.example.yeongpyo.androidstudy_coinhelper

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.yeongpyo.androidstudy_coinhelper.BaseUtil.BaseRecyclerAdapter
import com.example.yeongpyo.androidstudy_coinhelper.BaseUtil.BaseRecyclerHolder

class testFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.testlayout, container, false)
        (activity as? MainActivity)?.supportActionBar?.let {
            it.title = " Coin list 1"
            it.setDisplayHomeAsUpEnabled(false)
        }
        return view
    }

}
