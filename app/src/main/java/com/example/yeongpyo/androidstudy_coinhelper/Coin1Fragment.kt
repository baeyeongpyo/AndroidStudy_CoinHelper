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
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_coin1.*

class Coin1Fragment : Fragment() {

    val APIParsingName = CoinDB.BTC.name

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
       Observable.create<APIinterface> { APIinterface.retrofit.create(APIinterface::class.java)  }//just(APIinterface.retrofit.create(APIinterface::class.java))
               .flatMap(retrofitFlatMap)
               .subscribeOn(Schedulers.newThread())
               .subscribeOn(AndroidSchedulers.mainThread())
               .subscribe(::retrofitSubject, ::getFail, ::retrofitComplete)


    }
    val retrofitFlatMap = Function<APIinterface, Observable<*>>{
            Observable.just(it.getTrades(APIParsingName),
                    it.getOrderBook(CoinDB.BTC.name),
                    it.getTicker(CoinDB.BTC.name)) }

    private fun retrofitSubject( data : Any){
        when(data){
            is OrderBookData -> ::getOrderBookData
            is TredesData -> ::getTredesData
            is TickerData -> ::getTickerData
        }
    }

    private fun retrofitComplete(){
        Log.i("retrofit", "Complete!")
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

    fun getFail( t : Throwable ){}
    fun getTredesData(data : TredesData) {
//        data.completeOrders.forEach { println(it) }
       "TredesData".Log()
    }
    fun getOrderBookData(data : OrderBookData){
//        data.ask.forEach { println(it) }
        "Orderbook".Log()
    }
    fun getTickerData(data : TickerData){
        "Ticker".Log()
    }

    fun String.Log() : Int = Log.i("Coin1Fagment", this)
}

/*
    private fun getOrderBook() = APIinterface.retrofit.create(APIinterface::class.java)
            .getOrderBook(CoinDB.BTC.name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::getOrderBookData, ::getFail)

    private fun getTrades() = APIinterface.retrofit.create(APIinterface::class.java)
            .getTrades(CoinDB.BTC.name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::getTredesData, ::getFail)

    private fun getTicker() = APIinterface.retrofit.create(APIinterface::class.java)
            .getTicker(CoinDB.BTC.name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::getTickerData, ::getFail)
*/
