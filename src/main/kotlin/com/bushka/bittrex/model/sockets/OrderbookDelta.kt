package com.bushka.bittrex.model.sockets

import com.bushka.bittrex.model.markets.OrderBookEntry

data class OrderbookDelta(
        val marketSymbol: String,
        val depth: Int,
        override val sequence: Int,
        val bidDeltas: List<OrderBookEntry>,
        val askDeltas: List<OrderBookEntry>
) : Sequenced