package com.cluttered.cryptocurrency.model.markets

import java.math.BigDecimal

data class OrderBookEntry (
        val quantity: BigDecimal,
        val rate: BigDecimal
)