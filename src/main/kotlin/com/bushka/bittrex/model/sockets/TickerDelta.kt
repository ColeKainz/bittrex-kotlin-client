package com.bushka.bittrex.model.sockets

import com.bushka.bittrex.model.markets.Ticker

data class TickerDelta(
        val sequence: Int,
        val deltas: List<Ticker>
)