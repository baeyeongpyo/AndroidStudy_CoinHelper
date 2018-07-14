package com.example.yeongpyo.androidstudy_coinhelper

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.yeongpyo.androidstudy_coinhelper.BaseUtil.BaseRecyclerAdapter
import com.example.yeongpyo.androidstudy_coinhelper.BaseUtil.BaseRecyclerHolder
import kotlinx.android.synthetic.main.fragment_coin1.*

class Coin1Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coin1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.run {
            title = " Coin list 1"
            setDisplayHomeAsUpEnabled(true)
        }

        rv_coin_list.run {
            adapter = getTestAdpater1(this).apply {
                data = arrayListOf(
                        TestDB("Test1", "Test2"),
                        TestDB("Test3", "Test4"),
                        TestDB("Test5", "Test6"),
                        TestDB("Test7", "Test8")
                )
            }

        }
    }

    fun getTestAdpater1(listview: RecyclerView) = object : BaseRecyclerAdapter<TestDB>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
                object : BaseRecyclerHolder<TestDB>(R.layout.item_data, listview) {
                    override fun onViewCreate(item: TestDB?): Unit = with(itemView) {
                        findViewById<TextView>(R.id.Data1).text = item?.Data1
                        findViewById<TextView>(R.id.Data2).text = item?.Data2
                    }
                }
    }

}
