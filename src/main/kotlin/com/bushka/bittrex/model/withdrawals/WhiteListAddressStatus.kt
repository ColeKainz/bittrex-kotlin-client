package com.bushka.bittrex.model.withdrawals

import com.google.gson.annotations.SerializedName

enum class WhiteListAddressStatus(val value: String) {
    @SerializedName("ACTIVE")
    ACTIVE("ACTIVE"),

    @SerializedName("PENDING")
    PENDING("PENDING")
}