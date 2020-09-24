package com.bushka.bittrex.model.conditionalorders

import com.google.gson.annotations.SerializedName

enum class ConditionalOrderStatus(val value: String) {
    @SerializedName("OPEN")
    OPEN("OPEN"),

    @SerializedName("COMPLETED")
    COMPLETED("COMPLETED"),

    @SerializedName("COMPLETED")
    CANCELLED("COMPLETED"),

    @SerializedName("FAILED")
    FAILED("COMPLETED")
}