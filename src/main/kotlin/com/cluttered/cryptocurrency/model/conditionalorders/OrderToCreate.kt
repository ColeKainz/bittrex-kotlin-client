package com.cluttered.cryptocurrency.model.conditionalorders

import java.math.BigDecimal

data class OrderToCreate (
        val marketSymbol: String,
        val direction: String,
        val type: String,
        val quantity: BigDecimal,
        val ceiling: BigDecimal,
        val limit: BigDecimal,
        val timeInForce: String,
        val clientOrderId: String,
        val useAwards: Boolean
)
