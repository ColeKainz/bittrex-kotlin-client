package com.cluttered.cryptocurrency.model.markets

import com.google.gson.annotations.SerializedName

enum class CandleInterval(val value: String) {
    @SerializedName("MINUTE_1")
    MINUTE_1("MINUTE_1"),

    @SerializedName("MINUTE_5")
    MINUTE_5("MINUTE_5"),

    @SerializedName("HOUR_1")
    HOUR_1("HOUR_1"),

    @SerializedName("DAY_1")
    DAY_1("DAY_1")
}