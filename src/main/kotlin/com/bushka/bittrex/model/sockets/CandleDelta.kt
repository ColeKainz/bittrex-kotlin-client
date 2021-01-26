package com.bushka.bittrex.model.sockets

import com.bushka.bittrex.model.markets.Candle

data class CandleDelta(
        val marketSymbol: String,
        val interval: String,
        val delta: Candle,
        override val sequence: Int
) : Sequenced