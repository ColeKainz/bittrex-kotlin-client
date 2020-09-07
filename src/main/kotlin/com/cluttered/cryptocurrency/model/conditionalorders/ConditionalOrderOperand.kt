package com.cluttered.cryptocurrency.model.conditionalorders

import com.google.gson.annotations.SerializedName

enum class ConditionalOrderOperand(val value: String) {
    @SerializedName("LTE")
    LessThanOrEqual("LTE"),

    @SerializedName("GTE")
    GreaterThanOrEqual("GTE")
}