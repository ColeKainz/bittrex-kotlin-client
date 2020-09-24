package com.bushka.bittrex.model.markets

import com.google.gson.annotations.SerializedName

enum class MarketsStatus(val value: String) {
    @SerializedName("ONLINE")
    ONLINE("ONLINE"),

    @SerializedName("OFFLINE")
    OFFLINE("OFFLINE")
}