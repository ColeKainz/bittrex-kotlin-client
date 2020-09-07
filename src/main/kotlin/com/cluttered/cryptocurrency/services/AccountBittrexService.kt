package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.model.account.Account
import com.cluttered.cryptocurrency.model.account.AccountVolume
import com.cluttered.cryptocurrency.retrofit.BittrexObservable
import com.cluttered.cryptocurrency.retrofit.RetrofitFactory
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