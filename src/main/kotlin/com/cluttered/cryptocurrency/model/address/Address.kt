package com.cluttered.cryptocurrency.model.address

data class Address(
        val status: String,
        val currencySymbol: String,
        val cryptoAddress: String,
        val cryptoAddressTag: String
)