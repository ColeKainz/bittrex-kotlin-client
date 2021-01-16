package com.bushka.bittrex.model.sockets
import com.bushka.bittrex.model.balances.Balance

data class BalanceDelta(
        val accountId: String,
        val sequence: Int,
        val delta: Balance
)
