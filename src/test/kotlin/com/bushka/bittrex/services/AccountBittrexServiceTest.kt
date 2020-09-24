package com.bushka.bittrex.services

import com.bushka.bittrex.model.account.Account
import com.bushka.bittrex.retrofit.onFailure
import com.bushka.bittrex.retrofit.onSuccess
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AccountBittrexServiceTest {

    private val accountBittrexService by lazy {
        AccountBittrexService.create()
    }

    @Test
    fun testAccount() {
        var result: Account? = null
        accountBittrexService.getAccount()
                .onFailure { println(it.message) }
                .onSuccess { result = it }

        assertThat(result).isNotNull
    }
}