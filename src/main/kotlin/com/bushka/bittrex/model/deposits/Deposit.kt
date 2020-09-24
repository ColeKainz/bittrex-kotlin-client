package com.bushka.bittrex.model.deposits

import java.math.BigDecimal

data class Deposit (
        val id: String,
        val currencySymbol: String,
        val quantity: BigDecimal,
        val cryptoAddress: String,
        val cryptoAddressTag: String,
        val txId: String,
        val confirmations: Int,
        val updatedAt: String,
        val completedAt: String,
        val status: DepositStatus,
        val source: DepositSource
)