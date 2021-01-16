package com.bushka.bittrex.model.sockets

import com.bushka.bittrex.model.markets.Trade

data class TradeDelta(
        val sequence: Int,
        val marketSymbol: String,
        val deltas: List<Trade>
)