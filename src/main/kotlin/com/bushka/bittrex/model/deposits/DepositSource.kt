package com.bushka.bittrex.model.deposits

import com.google.gson.annotations.SerializedName

enum class DepositSource(val value: String) {
    @SerializedName("BLOCKCHAIN")
    BLOCKCHAIN("BLOCKCHAIN"),

    @SerializedName("WIRE_TRANSFER")
    WIRE_TRANSFER("WIRE_TRANSFER"),

    @SerializedName("CREDIT_CARD")
    CREDIT_CARD("CREDIT_CARD")
}