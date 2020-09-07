package com.cluttered.cryptocurrency.model.markets

import java.math.BigDecimal

data class MarketSummary (
        val high: BigDecimal,
        val low: BigDecimal,
        val volume: BigDecimal,
        val quoteVolume: BigDecimal,
        val percentChange: BigDecimal,
        val updatedAt: String
)