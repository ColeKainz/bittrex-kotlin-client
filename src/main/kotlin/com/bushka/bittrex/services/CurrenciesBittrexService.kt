package com.bushka.bittrex.services

import com.bushka.bittrex.model.currencies.Currency
import com.bushka.bittrex.network.BittrexObservable
import com.bushka.bittrex.network.retrofit.RetrofitFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.Result
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
    fun getCurrencies(): BittrexObservable<Response<List<Currency>>>

    @GET("v3/currencies/{symbol}")
    fun getCurrency(@Path("symbol") symbol: String): BittrexObservable<Response<Currency>>
}