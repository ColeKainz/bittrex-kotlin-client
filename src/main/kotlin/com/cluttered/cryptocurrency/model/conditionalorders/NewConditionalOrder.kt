package com.cluttered.cryptocurrency.model.conditionalorders

import java.math.BigDecimal

data class NewConditionalOrder (
        val id: String,
        val marketSymbol: String,
        val operand: ConditionalOrderOperand,
        val triggerPrice: BigDecimal,
        val trailingStopPercent: BigDecimal,
        val createdOrderId: String,
        val orderToCreate: NewOrder,
        val orderToCancel: NewCancelConditionalOrder,
        val clientConditionalOrderId: String
)