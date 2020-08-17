package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.model.*
import com.cluttered.cryptocurrency.retrofit.BittrexObservable
import com.cluttered.cryptocurrency.retrofit.RetrofitFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import java.math.BigDecimal
import java.util.*

interface AccountBittrexService {

    companion object {
        fun create(key: String = "", secret: String = "") = create(RetrofitFactory.create())
        fun create(retrofit: Retrofit): AccountBittrexService {
            return retrofit.create(AccountBittrexService::class.java)
        }
    }

    @GET("v3/account")
    fun getAccount(): BittrexObservable<Account>

    @GET("v3/account/volume")
    fun getAccountVolume(): BittrexObservable<AccountVolume>
}