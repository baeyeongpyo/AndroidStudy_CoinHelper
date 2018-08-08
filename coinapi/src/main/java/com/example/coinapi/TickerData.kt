package com.example.coinapi

data class TickerData (
    val result: String,
    val volume: String,
    val last: String,
    val yesterday_last: String,
    val timestamp: String,
    val yesterday_low: String,
    val high: String,
    val currency: String,
    val low: String,
    val errorCode: String,
    val yesterday_first: String,
    val yesterday_volume: String,
    val yesterday_high: String,
    val first: String
){

    val TickerBoo by lazy { 0 <= last.toLong() - first.toLong() }

    fun getCurrentPrinceValue() = first.comma()

    fun getPreviousDayValue() = first.comma()

    fun getDayBeforeValue() = """
                |${ (last.toLong() - first.toLong()).comma()}
                |${((last.toFloat() / first.toFloat())).decimalPoint2()}%
                """.trimMargin()

    fun getHighPriceValue() = high.comma()

    fun getLowPriceValue() = low.comma()

    fun getVolumeValue() = volume.decimalPoint0()

}