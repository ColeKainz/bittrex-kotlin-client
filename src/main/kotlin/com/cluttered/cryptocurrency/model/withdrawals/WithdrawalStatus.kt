package com.cluttered.cryptocurrency.model.withdrawals

import com.google.gson.annotations.SerializedName

enum class WithdrawalStatus(val value: String) {
    @SerializedName("REQUESTED")
    REQUESTED("REQUESTED"),

    @SerializedName("AUTHORIZED")
    AUTHORIZED("AUTHORIZED"),

    @SerializedName("PENDING")
    PENDING("PENDING"),

    @SerializedName("COMPLETED")
    COMPLETED("COMPLETED"),

    @SerializedName("ERROR_INVALID_ADDRESS")
    ERROR_INVALID_ADDRESS("ERROR_INVALID_ADDRESS"),

    @SerializedName("CANCELLED")
    CANCELLED("CANCELLED")
}