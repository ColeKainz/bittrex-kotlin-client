package com.bushka.bittrex.model.markets

import java.math.BigDecimal

data class Ticker (
        val symbol: String,
        val lastTradeRate: BigDecimal,
        val bidRate: BigDecimal,
        val askRate: BigDecimal
)