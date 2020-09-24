package com.bushka.bittrex.model.conditionalorders

import com.google.gson.annotations.SerializedName

enum class ConditionalOrderOperand(val value: String) {
    @SerializedName("LTE")
    LessThanOrEqual("LTE"),

    @SerializedName("GTE")
    GreaterThanOrEqual("GTE")
}