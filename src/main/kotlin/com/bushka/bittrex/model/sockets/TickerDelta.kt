package com.bushka.bittrex.model.sockets

import com.bushka.bittrex.model.markets.Ticker

data class TickerDelta(
        val deltas: List<Ticker>,
        override val sequence: Int
) : Sequenced