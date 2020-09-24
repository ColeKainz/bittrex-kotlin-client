package com.bushka.bittrex.model.deposits

import com.google.gson.annotations.SerializedName

enum class DepositStatus(val value: String) {
    @SerializedName("PENDING")
    PENDING("PENDING"),

    @SerializedName("COMPLETED")
    COMPLETED("COMPLETED"),

    @SerializedName("ORPHANED")
    ORPHANED("ORPHANED"),

    @SerializedName("INVALIDATED")
    INVALIDATED("INVALIDATED")
}