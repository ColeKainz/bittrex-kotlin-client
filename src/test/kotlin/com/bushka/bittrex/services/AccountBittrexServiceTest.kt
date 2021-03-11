package com.bushka.bittrex.services

import com.bushka.bittrex.model.account.Account
import com.bushka.bittrex.network.onFailure
import com.bushka.bittrex.network.onSuccess
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import retrofit2.Response
import retrofit2.adapter.rxjava2.Result

class AccountBittrexServiceTest {

    private val accountBittrexService by lazy {
        AccountBittrexService.create()
    }

    /*@Test
    fun testAccount() {
        var result: Account? = null
        accountBittrexService.getAccount()
                .onFailure { println(it.message) }
                .onSuccess { result = it.response()?.body()}

        assertThat(result).isNotNull
    }*/
}