package com.bushka.bittrex.services

import com.bushka.bittrex.model.account.Account
import com.bushka.bittrex.model.account.AccountVolume
import com.bushka.bittrex.model.executions.Execution
import com.bushka.bittrex.model.executions.ExecutionId
import com.bushka.bittrex.network.BittrexObservable
import com.bushka.bittrex.network.retrofit.RetrofitFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface ExecutionsBittrexService {

    companion object {
        fun create(key: String = "", secret: String = "") = create(RetrofitFactory.create(key, secret))
        fun create(retrofit: Retrofit): ExecutionsBittrexService {
            return retrofit.create(ExecutionsBittrexService::class.java)
        }
    }

    @GET("v3/executions")
    fun getExecution(): BittrexObservable<Response<Execution>>

    @GET("v3/executions/last-id")
    fun getLastExecution(@Query("marketSymbol") marketSymbol: String): BittrexObservable<Response<ExecutionId>>

    @GET("v3/executions/last-id")
    fun checkLastExecution(): BittrexObservable<Response<Unit>>
}