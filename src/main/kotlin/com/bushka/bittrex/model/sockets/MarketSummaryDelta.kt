package com.bushka.bittrex.model.sockets

import com.bushka.bittrex.model.markets.MarketSummary

data class MarketSummaryDelta(
        override val sequence: Int,
        val deltas: List<MarketSummary>
) : Sequenced