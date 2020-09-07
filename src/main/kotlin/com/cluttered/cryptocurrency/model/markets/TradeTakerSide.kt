package com.cluttered.cryptocurrency.model.markets

import com.google.gson.annotations.SerializedName

enum class TradeTakerSide(val value: String) {
    @SerializedName("BUY")
    BUY("BUY"),

    @SerializedName("SELL")
    SELL("SELL")
}