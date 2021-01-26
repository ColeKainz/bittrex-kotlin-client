package com.bushka.bittrex.model.sockets

import com.bushka.bittrex.model.conditionalorders.ConditionalOrder

data class ConditionalOrderDelta(
        val accountId: String,
        override val sequence: Int,
        val delta: ConditionalOrder
) : Sequenced