package com.example.yeongpyo.androidStudyCoinHelper

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yeongpyo.androidStudyCoinHelper.Adapter.CoinDataAdapter
import com.example.yeongpyo.androidStudyCoinHelper.databinding.FragmentCoin1Binding
import kotlinx.android.synthetic.main.fragment_coin1.*

class Coin1Fragment : Fragment(){

    private val adapterSupport = CoinDataAdapter()
    private val bidAdapter by lazy { adapterSupport.bidAdapterMaker() }
    private val askAdapter by lazy { adapterSupport.askAdapterMaker() }
    private val tradesCompleteOrdersAdapter by lazy { adapterSupport.tradesAdapterMaker() }
    private lateinit var bindingData : CoinViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coin1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingData = CoinViewModel()
        val dataBindingUtil = DataBindingUtil.bind<FragmentCoin1Binding>(view)
        dataBindingUtil?.includeData = bindingData
        (activity as? AppCompatActivity)?.supportActionBar?.run {
            title = " Coin list 1"
            setDisplayHomeAsUpEnabled(true)
        }

        rv_coin_list_ask.run { adapter = askAdapter }
        rv_coin_list_bid.run { adapter = bidAdapter }
        rv_coin_list_ordersbook.run { adapter = tradesCompleteOrdersAdapter }

    }
}
