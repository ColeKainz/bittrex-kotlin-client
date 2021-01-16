package com.bushka.bittrex.model.sockets

import com.bushka.bittrex.model.orders.Order

data class OrderDelta(
        val accountId: String,
        val sequence: Int,
        val delta: Order
)