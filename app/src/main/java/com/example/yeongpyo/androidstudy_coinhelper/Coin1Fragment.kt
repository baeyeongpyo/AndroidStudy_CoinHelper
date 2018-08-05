package com.example.yeongpyo.androidstudy_coinhelper

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coinapi.AskData
import com.example.coinapi.BidData
import com.example.coinapi.TickerData
import com.example.coinapi.TredesCompleteOrders
import com.example.yeongpyo.androidstudy_coinhelper.Adapter.CoinDataAdapter
import kotlinx.android.synthetic.main.fragment_coin1.*
import kotlinx.android.synthetic.main.include_dataview.*

class Coin1Fragment : Fragment(), CoinContract.View {

    override lateinit var presenter: CoinContract.Presenter
    val AdapterSupport = CoinDataAdapter()
    val BidAdapter by lazy { AdapterSupport.BidAdapterMaker(rv_coin_list_bid) }
    val AskAdapter by lazy { AdapterSupport.AskAdapterMaker(rv_coin_list_ask) }
    val TredesCompleteOrdersAdapter by lazy { AdapterSupport.TredesAdapterMaker(rv_coin_list_ordersbook) }

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

        rv_coin_list_ask.run { adapter = AskAdapter }
        rv_coin_list_bid.run { adapter = BidAdapter }
        rv_coin_list_ordersbook.run { adapter = TredesCompleteOrdersAdapter }

    }

    override fun setTredes(data: Array<TredesCompleteOrders>) = TredesCompleteOrdersAdapter.addData(*data)
    override fun setAsk(data: Array<AskData>) = AskAdapter.addData(*data)
    override fun setBid(data: Array<BidData>) = BidAdapter.addData(*data)
    override fun setTicker(data: TickerData) {
        CurrentPrince.text = data.getCurrentPrinceValue()
        PreviousDay.text = data.getCurrentPrinceValue()
        DayBefore.text = data.getDayBeforeValue()
        HighPrice.text = data.getHighPriceValue()
        LowPrice.text = data.getLowPriceValue()
        Volume.text = data.getVolumeValue()
    }
}
