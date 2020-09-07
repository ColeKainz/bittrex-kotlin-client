package com.cluttered.cryptocurrency.model.conditionalorders

import java.math.BigDecimal

data class NewOrder (
        val marketSymbol: String,
        val direction: ConditionalOrderDirection,
        val type: ConditionalOrderNewOrderType,
        val quantity: BigDecimal,
        val ceiling: BigDecimal,
        val limit: BigDecimal,
        val timeInForce: ConditionalOrderTimeInForce,
        val clientOrderId: String,
        val useAwards: Boolean
)
