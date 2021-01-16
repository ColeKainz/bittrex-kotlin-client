package com.bushka.bittrex.model.sockets

import com.bushka.bittrex.model.markets.Candle

data class CandleDelta(
        val sequence: Int,
        val marketSymbol: String,
        val interval: String,
        val delta: Candle
)