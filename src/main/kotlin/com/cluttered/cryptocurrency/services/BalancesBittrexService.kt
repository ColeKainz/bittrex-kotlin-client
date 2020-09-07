package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.model.balances.Balance
import com.cluttered.cryptocurrency.retrofit.BittrexObservable
import com.cluttered.cryptocurrency.retrofit.RetrofitFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.Path

interface BalancesBittrexService {
    companion object {
        fun create(key: String = "", secret: String = "") = create(RetrofitFactory.create(key, secret))
        fun create(retrofit: Retrofit): BalancesBittrexService {
            return retrofit.create(BalancesBittrexService::class.java)
        }
    }

    @GET("v3/balances")
    fun getBalances(): BittrexObservable<List<Balance>>

    @HEAD("v3/balances")
    fun checkBalances()

    @GET("v3/balances/{symbols}")
    fun getBalances(@Path("symbols") symbol: String): BittrexObservable<List<Balance>>
}