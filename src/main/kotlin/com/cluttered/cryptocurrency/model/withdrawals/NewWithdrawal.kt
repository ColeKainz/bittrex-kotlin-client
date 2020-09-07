package com.cluttered.cryptocurrency.model.withdrawals

import java.math.BigDecimal

data class NewWithdrawal (
        val currencySymbol: String,
        val quantity: BigDecimal,
        val cryptoAddress: String,
        val cryptoAddressTag: String
)