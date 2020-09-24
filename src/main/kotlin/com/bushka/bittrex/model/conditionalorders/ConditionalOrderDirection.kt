package com.bushka.bittrex.model.conditionalorders

import com.google.gson.annotations.SerializedName

enum class ConditionalOrderDirection(val value: String) {
    @SerializedName("BUY")
    BUY("BUY"),

    @SerializedName("SELL")
    SELL("SELL")
}