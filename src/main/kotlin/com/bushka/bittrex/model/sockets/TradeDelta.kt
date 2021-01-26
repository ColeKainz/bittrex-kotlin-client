package com.bushka.bittrex.model.sockets

import com.bushka.bittrex.model.markets.Trade

data class TradeDelta(
        override val sequence: Int,
        val marketSymbol: String,
        val deltas: List<Trade>
) : Sequenced