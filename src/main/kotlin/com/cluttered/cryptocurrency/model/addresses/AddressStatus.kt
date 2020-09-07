package com.cluttered.cryptocurrency.model.addresses

import com.google.gson.annotations.SerializedName

enum class AddressStatus {
    @SerializedName("REQUESTED")
    REQUESTED,

    @SerializedName("PROVISIONED")
    PROVISIONED
}