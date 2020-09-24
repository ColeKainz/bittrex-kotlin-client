package com.bushka.bittrex.model.addresses

data class Address (
        val status: AddressStatus,
        val currencySymbol: String,
        val cryptoAddress: String,
        val cryptoAddressTag: String
)