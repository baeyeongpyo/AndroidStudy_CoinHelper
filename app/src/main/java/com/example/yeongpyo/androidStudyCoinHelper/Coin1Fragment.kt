package com.example.yeongpyo.androidStudyCoinHelper

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coinapi.AskData
import com.example.coinapi.BidData
import com.example.coinapi.TickerData
import com.example.coinapi.TradesCompleteOrders
import com.example.yeongpyo.androidStudyCoinHelper.Adapter.CoinDataAdapter
import kotlinx.android.synthetic.main.fragment_coin1.*
import kotlinx.android.synthetic.main.include_dataview.*

class Coin1Fragment : Fragment(), CoinContract.View {

    override lateinit var presenter: CoinContract.Presenter
    private val adapterSupport = CoinDataAdapter()
    private val bidAdapter by lazy { adapterSupport.bidAdapterMaker() }
    private val askAdapter by lazy { adapterSupport.askAdapterMaker() }
    private val tradesCompleteOrdersAdapter by lazy { adapterSupport.tradesAdapterMaker() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coin1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = CoinPresenter(this)
        presenter.start()
        (activity as? AppCompatActivity)?.supportActionBar?.run {
            title = " Coin list 1"
            setDisplayHomeAsUpEnabled(true)
        }

        rv_coin_list_ask.run { adapter = askAdapter }
        rv_coin_list_bid.run { adapter = bidAdapter }
        rv_coin_list_ordersbook.run { adapter = tradesCompleteOrdersAdapter }

    }

    override fun setTrades(data: List<TradesCompleteOrders>) = tradesCompleteOrdersAdapter.addData(data)
    override fun setAsk(data: List<AskData>) = askAdapter.addData(data)
    override fun setBid(data: List<BidData>) = bidAdapter.addData(data)
    override fun setTicker(data: TickerData) {
        CurrentPrince.text = data.getCurrentPrinceValue()
        PreviousDay.text = data.getCurrentPrinceValue()
        DayBefore.text = data.getDayBeforeValue()
        HighPrice.text = data.getHighPriceValue()
        LowPrice.text = data.getLowPriceValue()
        Volume.text = data.getVolumeValue()
    }
}
