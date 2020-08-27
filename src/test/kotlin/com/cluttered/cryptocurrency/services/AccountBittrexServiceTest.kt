package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.model.account.Account
import com.cluttered.cryptocurrency.retrofit.onFailure
import com.cluttered.cryptocurrency.retrofit.onSuccess
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