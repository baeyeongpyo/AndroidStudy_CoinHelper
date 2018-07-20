package com.example.yeongpyo.androidstudy_coinhelper

import org.junit.Test

import org.junit.Assert.*
import java.text.DecimalFormat
import java.util.regex.Pattern

/**
 * Example local unit converterBefore, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testCal(){
        val testAnumber = 88844F
        val testBnumber = 4123F

        val result = testAnumber / testBnumber
        println(DecimalFormat(".##").format(result))


        Pattern.compile("^\\d")
        val testprint = Pattern.matches("^\\d", result.toString())


        println(DecimalFormat("#,###").format("1578157531".toInt()))
        println("551848713".comma())

    }
    fun String.comma() = DecimalFormat("#,###").format(this.toInt())

}
