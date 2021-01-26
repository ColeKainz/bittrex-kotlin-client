package com.bushka.bittrex.model.sockets

import com.bushka.bittrex.model.orders.Order

data class OrderDelta(
        val accountId: String,
        override val sequence: Int,
        val delta: Order
) : Sequenced