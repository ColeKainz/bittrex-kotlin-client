package com.cluttered.cryptocurrency.model.markets

import java.math.BigDecimal

data class Market (
        val symbol: String,
        val baseCurrencySymbol: String,
        val quoteCurrencySymbol: String,
        val minTradeSize: BigDecimal,
        val precision: Int,
        val status: String,
        val createdAt: String,
        val notice: String,
        val prohibitedIn: List<String>
)