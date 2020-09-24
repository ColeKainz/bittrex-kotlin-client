package com.bushka.bittrex.services

import com.bushka.bittrex.model.ping.Ping
import com.bushka.bittrex.retrofit.BittrexObservable
import com.bushka.bittrex.retrofit.RetrofitFactory
import retrofit2.Retrofit
import retrofit2.http.GET

interface PingBittrexService {
    companion object {
        fun create(key: String = "", secret: String = "") = create(RetrofitFactory.create(key, secret))
        fun create(retrofit: Retrofit): PingBittrexService {
            return retrofit.create(PingBittrexService::class.java)
        }
    }

    @GET("v3/ping")
    fun getPing(): BittrexObservable<Ping>
}