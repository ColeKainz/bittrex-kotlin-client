package com.cluttered.cryptocurrency.model.conditionalorders

data class NewCancelConditionalOrder (
        val type: ConditionalOrderNewCancelType,
        val id: String
)
