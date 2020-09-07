package com.cluttered.cryptocurrency.model.markets

import com.google.gson.annotations.SerializedName

enum class OrderBookDepth(val value: Int) {
    @SerializedName("1")
    SHALLOW(1),

    @SerializedName("25")
    MID(25),

    @SerializedName("500")
    DEEP(500)
}