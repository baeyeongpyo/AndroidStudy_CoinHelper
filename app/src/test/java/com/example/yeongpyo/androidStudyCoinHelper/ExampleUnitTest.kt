package com.example.yeongpyo.androidStudyCoinHelper

import io.reactivex.Observable
import io.reactivex.functions.Function
import io.reactivex.internal.operators.observable.ObservableInterval
import org.junit.Test

import org.junit.Assert.*
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

/**
 * Example local unit converterBefore, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {


        ObservableInterval.interval(200, TimeUnit.MICROSECONDS)
                .subscribe { observable_loop.retryWhen(retrywhen_).subscribe { println(it) } }

        Thread.sleep(5000)
    }

    val observable_loop = Observable.just("").map { getTESTCase().toInt() }

    val retrywhen_ = Function<Observable<out Throwable>, Observable<*>>{
//        if( count < 5 ) observable_loop else observable_loop
            Observable.just("A")
    }

    var count = 0
    fun getTESTCase() : String = when ( count ++ ){
        0 -> "COUNT 1"
        1 -> "COUNT 2"
        2 -> "COUNT 3"
        3 -> "COUNT 4"
        4 -> {count = 0 ; "1212"; }
        else -> "TEST OVER"
    }

}
