package com.example.coinapi

import java.text.DecimalFormat

@Suppress("NOTHING_TO_INLINE")
open class APIDecimalSupport {
    fun String.comma() = DecimalFormat("#,###").format(this.toInt())
    fun Long.comma() = DecimalFormat("#,###").format(this.toInt())

    fun String.decimalPoint2() = DecimalFormat(".##").format(this.toFloat())
    fun Float.decimalPoint2() = DecimalFormat(".##").format(this)
    fun Long.decimalPoint2() = DecimalFormat(".##").format(this)

    fun String.decimalPoint0() = this.toDouble().toInt().toString()
    fun Float.decimalPoint0() = this.toDouble().toInt().toString()
    fun Long.decimalPoint0() = this.toDouble().toInt().toString()


    fun StringHighOrder(string: Any, body: (Any) -> String) = body(string)
    fun HighOrderComma(deci: Any) = DecimalFormat("#,###").format("$deci".toLong()).toString()
    fun HighOrderDicima2(deci: Any) = DecimalFormat("#.##").format(deci as Long).toString()
    fun HighOrderDicima0(deci: Any) = "$deci".toFloat().toInt()

}