package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.model.currencies.Currency
import com.cluttered.cryptocurrency.model.deposits.Deposit
import com.cluttered.cryptocurrency.model.deposits.DepositStatus
import com.cluttered.cryptocurrency.retrofit.BittrexObservable
import com.cluttered.cryptocurrency.retrofit.RetrofitFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.Path
import retrofit2.http.Query

interface DepositsBittrexService {
    companion object {
        fun create(key: String = "", secret: String = "") = create(RetrofitFactory.create(key, secret))
        fun create(retrofit: Retrofit): DepositsBittrexService {
            return retrofit.create(DepositsBittrexService::class.java)
        }
    }

    @GET("v3/deposits/open")
    fun getOpenDeposits(@Query("status") status: DepositStatus? = null, @Query("currencySymbol") symbol: String? = null): BittrexObservable<List<Deposit>>

    @HEAD("v3/deposits/open")
    fun checkOpenDeposits()

    @GET("v3/deposits/closed")
    fun getClosedDeposits(@Query("status") status: DepositStatus? = null,
                        @Query("currencySymbol") symbol: String? = null,
                        @Query("nextPageToken") nextPageToken: String? = null,
                        @Query("previousPageToken") previousPageToken: String? = null,
                        @Query("pageSize") pageSize: String? = null,
                        @Query("startDate") startDate: String? = null,
                        @Query("endDate") endDate: String? = null): BittrexObservable<List<Deposit>>

    @GET("v3/deposits/ByTxId/{txId}")
    fun getOpenDeposits(@Path("txId") txId: String): BittrexObservable<List<Deposit>>

    @GET("v3/deposits/{depositId}")
    fun getDeposit(@Path("depositId") depositId: String): BittrexObservable<Deposit>
}