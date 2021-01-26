package com.bushka.bittrex.model.sockets
import com.bushka.bittrex.model.balances.Balance

data class BalanceDelta(
        val accountId: String,
        val delta: Balance,
        override val sequence: Int
) : Sequenced
