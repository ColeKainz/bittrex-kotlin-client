package com.bushka.bittrex.model.orders

import com.google.gson.annotations.SerializedName

enum class OrderType(val value: String) {
    @SerializedName("LIMIT")
    LIMIT("LIMIT"),

    @SerializedName("MARKET")
    MARKET("MARKET"),

    @SerializedName("CEILING_LIMIT")
    CEILING_LIMIT("CEILING_LIMIT"),

    @SerializedName("CEILING_MARKET")
    CEILING_MARKET("CEILING_MARKET")
}