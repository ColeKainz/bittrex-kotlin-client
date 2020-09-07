package com.cluttered.cryptocurrency.model.markets

import java.math.BigDecimal

data class Candle (
        val startsAt: String,
        val open: BigDecimal,
        val high: BigDecimal,
        val low: BigDecimal,
        val close: BigDecimal,
        val volume: BigDecimal,
        val quoteVolume: BigDecimal
)