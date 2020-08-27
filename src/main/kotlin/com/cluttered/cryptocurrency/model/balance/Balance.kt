package com.cluttered.cryptocurrency.model.balance

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Balance(
        val currencySymbol: String,
        val total: BigDecimal,
        val available: BigDecimal,
        val updatedAt: String
)