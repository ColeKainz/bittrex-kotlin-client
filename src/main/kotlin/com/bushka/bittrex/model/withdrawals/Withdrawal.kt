package com.bushka.bittrex.model.withdrawals

import java.math.BigDecimal

data class Withdrawal (
        val id: String,
        val currencySymbol: String,
        val quantity: BigDecimal,
        val cryptoAddress: String,
        val cryptoAddressTag: String,
        val txCost: BigDecimal,
        val txId: String,
        val status: WithdrawalStatus,
        val createdAt: String,
        val completedAt: String
)