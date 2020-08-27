package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.model.balance.Balance
import com.cluttered.cryptocurrency.retrofit.BittrexObservable
import com.cluttered.cryptocurrency.retrofit.RetrofitFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface BalanceBittrexService {
    companion object {
        fun create(key: String = "", secret: String = "") = create(RetrofitFactory.create())
        fun create(retrofit: Retrofit): BalanceBittrexService {
            return retrofit.create(BalanceBittrexService::class.java)
        }
    }

    @GET("v3/balances")
    fun getBalances(): BittrexObservable<List<Balance>>

    @GET("v3/balances/{symbols}")
    fun getBalances(@Path("symbols") symbol: String): BittrexObservable<List<Balance>>
}