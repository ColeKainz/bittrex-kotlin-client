package com.bushka.bittrex.services

import com.bushka.bittrex.model.balances.Balance
import com.bushka.bittrex.network.BittrexObservable
import com.bushka.bittrex.network.retrofit.RetrofitFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.Result
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
    fun getBalances(): BittrexObservable<Response<List<Balance>>>

    @HEAD("v3/balances")
    fun checkBalances(): BittrexObservable<Response<Void>>
    //fun checkBalances(): Void

    @GET("v3/balances/{symbols}")
    fun getBalance(@Path("symbols") symbol: String): BittrexObservable<Response<Balance>>
}
