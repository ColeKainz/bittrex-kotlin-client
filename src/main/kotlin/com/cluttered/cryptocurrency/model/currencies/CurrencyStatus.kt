package com.cluttered.cryptocurrency.model.currencies

import com.google.gson.annotations.SerializedName

enum class CurrencyStatus(val value: String) {
    @SerializedName("ONLINE")
    ONLINE("ONLINE"),

    @SerializedName("OFFLINE")
    OFFLINE("OFFLINE")
}