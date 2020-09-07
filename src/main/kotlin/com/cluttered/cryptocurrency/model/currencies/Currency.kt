package com.cluttered.cryptocurrency.model.currencies

import java.math.BigDecimal

data class Currency (
        val symbol: String,
        val name: String,
        val coinType: String,
        val status: CurrencyStatus,
        val minConfirmations: Int,
        val notice: String,
        val txFee: BigDecimal,
        val logoUrl: String,
        val prohibitedIn: List<String>
)