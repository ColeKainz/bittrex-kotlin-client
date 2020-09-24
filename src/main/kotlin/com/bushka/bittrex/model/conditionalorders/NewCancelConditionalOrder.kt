package com.bushka.bittrex.model.conditionalorders

data class NewCancelConditionalOrder (
        val type: ConditionalOrderNewCancelType,
        val id: String
)
