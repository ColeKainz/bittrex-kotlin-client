package com.bushka.bittrex.model.orders

import java.math.BigDecimal

data class Execution (
        val id: String,
        val marketSymbol: String,
        val executedAt: String,
        val quantity: BigDecimal,
        val rate: BigDecimal,
        val orderId: String,
        val commission: BigDecimal,
        val isTaker: Boolean
)