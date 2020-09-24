package com.bushka.bittrex.model.orders

import com.google.gson.annotations.SerializedName

enum class OrderDirection(val value: String) {
    @SerializedName("BUY")
    BUY("BUY"),

    @SerializedName("SELL")
    SELL("SELL")
}