package com.example.yeongpyo.androidstudy_coinhelper

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

class Coin1Fragment : Fragment() {

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

    fun getTestAdpater1(listview: RecyclerView) = object : BaseRecyclerAdapter<TestDB>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
                object : BaseRecyclerHolder<TestDB>(R.layout.item_data, listview) {
                    override fun onViewCreate(item: TestDB?): Unit = with(itemView) {
                        findViewById<TextView>(R.id.Data1).text = item?.Data1
                        findViewById<TextView>(R.id.Data2).text = item?.Data2
                    }
                }
    }

    fun getFail(t: Throwable) {
        "ERR Print".LogPrint()
    }

    fun getTredesData(data: TredesData) {
        data.completeOrders[0]
        "Tredes OK".LogPrint()
    }

    fun getOrderBookData(data: OrderBookData) {
        data.ask.forEach { }
        data.bid.forEach { }
        "OrderBook OK".LogPrint()
    }

    fun getTickerData(data: TickerData) {
        data.currency
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
