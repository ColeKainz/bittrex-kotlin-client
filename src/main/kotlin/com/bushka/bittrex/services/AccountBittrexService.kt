package com.bushka.bittrex.services

import com.bushka.bittrex.model.account.Account
import com.bushka.bittrex.model.account.AccountVolume
import com.bushka.bittrex.network.BittrexObservable
import com.bushka.bittrex.network.retrofit.RetrofitFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET

interface AccountBittrexService {

    companion object {
        fun create(key: String = "", secret: String = "") = create(RetrofitFactory.create(key, secret))
        fun create(retrofit: Retrofit): AccountBittrexService {
            return retrofit.create(AccountBittrexService::class.java)
        }
    }

    @GET("v3/account")
    fun getAccount(): BittrexObservable<Response<Account>>

    @GET("v3/account/volume")
    fun getAccountVolume(): BittrexObservable<Response<AccountVolume>>
}