package com.bushka.bittrex.model.balances

import java.math.BigDecimal

data class Balance (
        val currencySymbol: String,
        val total: BigDecimal,
        val available: BigDecimal,
        val updatedAt: String
)