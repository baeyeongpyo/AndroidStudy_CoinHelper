package com.example.coinapi

import java.text.DecimalFormat

    fun String.comma(): String = DecimalFormat("#,###").format(this.toInt())
    fun Long.comma(): String = DecimalFormat("#,###").format(this.toInt())
    fun Float.decimalPoint2(): String = DecimalFormat(".##").format(this)
    fun String.decimalPoint0() = this.toDouble().toInt().toString()