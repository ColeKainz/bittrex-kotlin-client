package com.bushka.bittrex.model.markets

data class OrderBook (
    val bid: List<OrderBookEntry>,
    val ask: List<OrderBookEntry>
)