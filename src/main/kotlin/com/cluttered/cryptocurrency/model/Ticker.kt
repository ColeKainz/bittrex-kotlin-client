package com.cluttered.cryptocurrency.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Ticker(
        val symbol: String,
        val bidRate: BigDecimal,
        val askRate: BigDecimal,
        val lastTradeRate: BigDecimal
)