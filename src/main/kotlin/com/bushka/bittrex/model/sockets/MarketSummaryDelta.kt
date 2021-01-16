package com.bushka.bittrex.model.sockets

import com.bushka.bittrex.model.markets.MarketSummary

data class MarketSummaryDelta(
        val sequence: Int,
        val deltas: List<MarketSummary>
)