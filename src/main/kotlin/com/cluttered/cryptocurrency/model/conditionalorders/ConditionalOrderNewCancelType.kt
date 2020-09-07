package com.cluttered.cryptocurrency.model.conditionalorders

import com.google.gson.annotations.SerializedName

enum class ConditionalOrderNewCancelType(val value: String) {
    @SerializedName("ORDER")
    ORDER("ORDER"),

    @SerializedName("CONDITIONAL_ORDER")
    CONDITIONAL_ORDER("CONDITIONAL_ORDER")
}