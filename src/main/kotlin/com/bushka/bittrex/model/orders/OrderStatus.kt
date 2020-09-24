package com.bushka.bittrex.model.orders

import com.google.gson.annotations.SerializedName

enum class OrderStatus(val value: String) {
    @SerializedName("OPEN")
    OPEN("OPEN"),

    @SerializedName("CLOSED")
    CLOSED("CLOSED")
}