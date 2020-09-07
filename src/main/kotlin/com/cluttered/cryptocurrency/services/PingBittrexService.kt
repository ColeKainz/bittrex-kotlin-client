package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.model.ping.Ping
import com.cluttered.cryptocurrency.retrofit.BittrexObservable
import com.cluttered.cryptocurrency.retrofit.RetrofitFactory
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