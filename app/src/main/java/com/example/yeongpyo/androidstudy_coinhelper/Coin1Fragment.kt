package com.example.yeongpyo.androidstudy_coinhelper

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coinapi.*
import com.example.yeongpyo.androidstudy_coinhelper.Adapter.CoinDataAdapter
import com.example.yeongpyo.androidstudy_coinhelper.RxComm.APICallRX
import io.reactivex.internal.operators.observable.ObservableInterval
import kotlinx.android.synthetic.main.fragment_coin1.*
import kotlinx.android.synthetic.main.include_dataview.*
import java.util.concurrent.TimeUnit

class Coin1Fragment : Fragment() {

    val DecimalSupport = APIDecimalSupport()
    val AdapterSupport = CoinDataAdapter()
    val ObservableSupport = APICallRX(CoinDB.BTC.coinName)
    val BidAdapter by lazy { AdapterSupport.BidAdapterMaker(rv_coin_list_bid) }
    val AskAdapter by lazy { AdapterSupport.AskAdapterMaker(rv_coin_list_ask) }
    val TredesCompleteOrdersAdapter by lazy { AdapterSupport.TredesAdapterMaker(rv_coin_list_ordersbook) }
//    val TredesCompleteOrdersAdapter
//        by lazy { AdapterSupport.HighOrderAdapter(rv_coin_list_ordersbook) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coin1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.run {
            title = " Coin list 1"
            setDisplayHomeAsUpEnabled(true)
        }

        rv_coin_list_ask.run { adapter = AskAdapter }
        rv_coin_list_bid.run { adapter = BidAdapter }
        rv_coin_list_ordersbook.run { adapter = TredesCompleteOrdersAdapter }

        RxIntervable.subscribe {
            getOrderBook()
            getTrades()
            getTicker()
        }

    }

    val RxIntervable = ObservableInterval.interval(2, 3, TimeUnit.SECONDS)


    private fun getOrderBook() = ObservableSupport.OrderBookObservable.subscribe(::getOrderBookData, ::getFail)

    private fun getTrades() = ObservableSupport.TredesObsevable.subscribe(::getTredesData, ::getFail)

    private fun getTicker() = ObservableSupport.TickerObservable.subscribe(::getTickerData, ::getFail)

    fun getFail(t: Throwable) {
        "ERR Print".LogPrint()
    }

    fun getTredesData(data: TredesData) {
        TredesCompleteOrdersAdapter.addData(*data.completeOrders.toTypedArray())
        "Tredes OK".LogPrint()
    }

    fun getOrderBookData(data: OrderBookData) {
        AskAdapter.addData(*data.ask.toTypedArray())
        BidAdapter.addData(*data.bid.toTypedArray())
        "OrderBook OK".LogPrint()
    }

    @SuppressLint("SetTextI18n")
    fun getTickerData(data: TickerData) {
        data.run {
            val TickerBoo = 0 <= last.toLong() - first.toLong()
            CurrentPrince.text = with(DecimalSupport) { last.comma() }
            PreviousDay.text = with(DecimalSupport) { first.comma() }
            DayBefore.text = """
                |${with(DecimalSupport) { (last.toLong() - first.toLong()).comma() }}
                |${with(DecimalSupport) { ((last.toFloat() / first.toFloat())).decimalPoint2() }}%
                """.trimMargin()
            HighPrice.text = with(DecimalSupport) { high.comma() }
            LowPrice.text = with(DecimalSupport) { low.comma() }
            Volume.text = with(DecimalSupport) { volume.decimalPoint0() }
        }
        "Ticker OK".LogPrint()
    }

    private fun String.LogPrint() = Log.i("RetroFitTest", this)
}
