package com.bushka.bittrex.model.sockets

import com.bushka.bittrex.model.deposits.Deposit

data class DepositDelta(
        val accountId: String,
        override val sequence: Int,
        val delta: Deposit
) : Sequenced