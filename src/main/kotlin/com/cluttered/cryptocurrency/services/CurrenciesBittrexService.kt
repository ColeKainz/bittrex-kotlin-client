package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.model.currencies.Currency
import com.cluttered.cryptocurrency.retrofit.BittrexObservable
import com.cluttered.cryptocurrency.retrofit.RetrofitFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrenciesBittrexService {
    companion object {
        fun create(key: String = "", secret: String = "") = create(RetrofitFactory.create(key, secret))
        fun create(retrofit: Retrofit): CurrenciesBittrexService {
            return retrofit.create(CurrenciesBittrexService::class.java)
        }
    }

    @GET("v3/currencies")
    fun getCurrencies(): BittrexObservable<List<Currency>>

    @GET("v3/currencies/{symbol}")
    fun getCurrency(@Path("symbol") symbol: String): BittrexObservable<Currency>
}