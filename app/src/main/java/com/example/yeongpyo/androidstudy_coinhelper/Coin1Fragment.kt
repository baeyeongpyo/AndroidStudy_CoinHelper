package com.example.yeongpyo.androidstudy_coinhelper

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.coinapi.*
import com.example.yeongpyo.androidstudy_coinhelper.BaseUtil.BaseRecyclerAdapter
import com.example.yeongpyo.androidstudy_coinhelper.BaseUtil.BaseRecyclerHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_coin1.*
import kotlinx.android.synthetic.main.include_dataview.*

class Coin1Fragment : Fragment() {

    val DecimalSupport = APIDecimalSupport()

    val BidAdapter = object : BaseRecyclerAdapter<BidData>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
                object : BaseRecyclerHolder<BidData>(R.layout.item_data, rv_coin_list_bid) {
                    override fun onViewCreate(item: BidData?): Unit = with(itemView) {
                        findViewById<TextView>(R.id.Data1).text = with(DecimalSupport){item?.price?.comma() }
                        findViewById<TextView>(R.id.Data2).text = item?.qty
                    }
                }
    }


    val AskAdapter = object : BaseRecyclerAdapter<AskData>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
                object : BaseRecyclerHolder<AskData>(R.layout.item_data, rv_coin_list_ask) {
                    override fun onViewCreate(item: AskData?): Unit = with(itemView) {
                        findViewById<TextView>(R.id.Data1).text = item?.qty
                        findViewById<TextView>(R.id.Data2).text = with(DecimalSupport){item?.price?.comma()}
                    }
                }
    }

    val TredesCompleteOrdersAdapter = object : BaseRecyclerAdapter<TredesCompleteOrders>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
                object : BaseRecyclerHolder<TredesCompleteOrders>(R.layout.item_subdata, rv_coin_list_ordersbook) {
                    override fun onViewCreate(item: TredesCompleteOrders?): Unit = with(itemView) {
                        findViewById<TextView>(R.id.Data1).text = with(DecimalSupport){item?.price?.comma()}
                        findViewById<TextView>(R.id.Data2).text = item?.qty
                    }
                }
    }


    val APIParsingName = CoinDB.BTC.coinName

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
        getOrderBook()
        getTrades()
        getTicker()

    }

    private fun getOrderBook() = APIinterface.retrofit.create(APIinterface::class.java)
            .getOrderBook(APIParsingName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::getOrderBookData, ::getFail)

    private fun getTrades() = APIinterface.retrofit.create(APIinterface::class.java)
            .getTrades(APIParsingName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::getTredesData, ::getFail)

    private fun getTicker() = APIinterface.retrofit.create(APIinterface::class.java)
            .getTicker(APIParsingName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::getTickerData, ::getFail)


    private fun retrofitComplete() {
        Log.i("RetroFitTest", "Complete")
    }

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
        data.run{
            val TickerBoo = 0 <= last.toLong() - first.toLong()
            CurrentPrince.text = with(DecimalSupport){last.comma()}
            PreviousDay.text = with(DecimalSupport){first.comma()}
            DayBefore.text = """
                |${with(DecimalSupport){ (last.toLong() - first.toLong()).comma()}}
                |${with(DecimalSupport){ ((last.toFloat() / first.toFloat())).decimalPoint2()}}%
                """.trimMargin()
            HighPrice.text = with(DecimalSupport){high.comma()}
            LowPrice.text = with(DecimalSupport){low.comma()}
            Volume.text = with(DecimalSupport){volume.decimalPoint0()}
        }
        "Ticker OK".LogPrint()
    }

    private fun String.LogPrint() = Log.i("RetroFitTest", this)
}



/*
        Observable.just(APIinterface.retrofit.create(APIinterface::class.java))
                .flatMap(retrofitFlatMap)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(::retrofitSubject, ::getFail, ::retrofitComplete)


val retrofitFlatMap = Function<APIinterface, Observable<*>> {
    Observable.just(it.getTrades(APIParsingName),
            it.getOrderBook(APIParsingName),
            it.getTicker(APIParsingName))
}

private fun<T> retrofitSubject(data: T) {
    when (data) {
        is OrderBookData -> ::getOrderBookData
        is TredesData -> ::getTredesData
        is TickerData -> ::getTickerData
        else -> {
            Log.i("RetroFitTest", "Not Check")
        }
    }
}*/
