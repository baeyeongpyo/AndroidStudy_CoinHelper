package com.example.coinapi

import com.google.gson.annotations.SerializedName

class TradesData {
    @SerializedName( "errorCode") var errorCode : String = ""
    @SerializedName( "timestamp") var timestamp : String = ""
    @SerializedName( "completeOrders") var completeOrders : String = ""

    inner class innerData{
        @SerializedName( "timestamp") var timestamp : String = ""
        @SerializedName( "price") var price : String = ""
        @SerializedName( "qty") var qty : String = ""
    }

}