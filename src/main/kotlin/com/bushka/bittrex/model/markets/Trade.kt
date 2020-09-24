package com.bushka.bittrex.model.markets

import java.math.BigDecimal

data class Trade (
        val id: String,
        val executedAt: String,
        val quantity: BigDecimal,
        val rate: BigDecimal,
        val takerSide: TradeTakerSide
)