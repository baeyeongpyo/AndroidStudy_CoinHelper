package com.example.coinapi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TredesData(
    @SerializedName( "errorCode") var errorCode : String,
    @SerializedName( "timestamp") var timestamp : String,
    @SerializedName( "completeOrders") var completeOrders : List<innerData>,
    @SerializedName( "result") var result : String,
    @SerializedName( "currenct") var currency : String
)
data class innerData(
    @SerializedName( "is_ask") var isAsk : String,
    @SerializedName( "timestamp") var timestamp : String,
    @SerializedName( "price") var price : String,
    @SerializedName( "qty") var qty : String
)