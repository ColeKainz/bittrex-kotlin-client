package com.cluttered.cryptocurrency.model.conditionalorders

import com.google.gson.annotations.SerializedName

enum class ConditionalOrderDirection(val value: String) {
    @SerializedName("BUY")
    BUY("BUY"),

    @SerializedName("SELL")
    SELL("SELL")
}