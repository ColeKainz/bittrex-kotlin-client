package com.bushka.bittrex.services

import com.bushka.bittrex.model.account.Account
import com.bushka.bittrex.model.account.AccountVolume
import com.bushka.bittrex.retrofit.BittrexObservable
import com.bushka.bittrex.retrofit.RetrofitFactory
import retrofit2.Retrofit
import retrofit2.http.GET

interface AccountBittrexService {

    companion object {
        fun create(key: String = "", secret: String = "") = create(RetrofitFactory.create(key, secret))
        fun create(retrofit: Retrofit): AccountBittrexService {
            return retrofit.create(AccountBittrexService::class.java)
        }
    }

    @GET("v3/account")
    fun getAccount(): BittrexObservable<Account>

    @GET("v3/account/volume")
    fun getAccountVolume(): BittrexObservable<AccountVolume>
}