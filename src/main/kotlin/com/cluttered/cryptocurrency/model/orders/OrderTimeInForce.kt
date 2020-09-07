package com.cluttered.cryptocurrency.model.orders

import com.google.gson.annotations.SerializedName

enum class OrderTimeInForce(val value: String) {
    @SerializedName("GOOD_TIL_CANCELED")
    GOOD_TIL_CANCELED("GOOD_TIL_CANCELED"),

    @SerializedName("IMMEDIATE_OR_CANCEL")
    IMMEDIATE_OR_CANCEL("IMMEDIATE_OR_CANCEL"),

    @SerializedName("FILL_OR_KILL")
    FILL_OR_KILL("FILL_OR_KILL"),

    @SerializedName("POST_ONLY_GOOD_TIL_CANCELLED")
    POST_ONLY_GOOD_TIL_CANCELLED("POST_ONLY_GOOD_TIL_CANCELLED"),

    @SerializedName("BUY_NOW")
    BUY_NOW("BUY_NOW")
}