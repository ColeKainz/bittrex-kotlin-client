package com.cluttered.cryptocurrency.model.conditionalorders

import java.math.BigDecimal

data class NewConditionalOrder (
        val id: String,
        val marketSymbol: String,
        val operand: String,
        val triggerPrice: BigDecimal,
        val trailingStopPercent: BigDecimal,
        val createdOrderId: String,
        val orderToCreate: OrderToCreate,
        val orderToCancel: OrderToCancel,
        val clientConditionalOrderId: String
)