package com.bushka.bittrex

import com.bushka.bittrex.network.retrofit.RetrofitFactory
import com.bushka.bittrex.services.*

class BittrexClient(key: String, secret: String) {

    private val retrofit = RetrofitFactory.create(key, secret)

    val account by lazy {
        AccountBittrexService.create(retrofit)
    }

    val addresses by lazy {
        AddressesBittrexService.create(retrofit)
    }

    val balance by lazy {
        BalancesBittrexService.create(retrofit)
    }

    val conditionalOrders by lazy {
        ConditionalOrdersBittrexService.create(retrofit)
    }

    val currencies by lazy {
        CurrenciesBittrexService.create(retrofit)
    }

    val deposits by lazy {
        DepositsBittrexService.create(retrofit)
    }

    val markets by lazy {
        MarketsBittrexService.create(retrofit)
    }

    val orders by lazy {
        OrdersBittrexService.create(retrofit)
    }

    val exchanges by lazy {
        ExecutionsBittrexService.create(retrofit)
    }

    val ping by lazy {
        PingBittrexService.create(retrofit)
    }

    val withdrawal by lazy {
        WithdrawalBittrexService.create(retrofit)
    }
}
