package com.bushka.bittrex.services

import com.bushka.bittrex.model.markets.*
import com.bushka.bittrex.network.BittrexObservable
import com.bushka.bittrex.network.retrofit.RetrofitFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.Path
import retrofit2.http.Query

interface MarketsBittrexService {
    companion object {
        fun create(key: String = "", secret: String = "") = create(RetrofitFactory.create(key, secret))
        fun create(retrofit: Retrofit): MarketsBittrexService {
            return retrofit.create(MarketsBittrexService::class.java)
        }
    }

    @GET("v3/markets")
    fun getMarkets(): BittrexObservable<Response<List<Market>>>

    @GET("v3/markets/{marketSymbol}")
    fun getMarket(@Path("marketSymbol") symbol: String): BittrexObservable<Response<Market>>

    @GET("v3/markets/summaries")
    fun getMarketSummaries(): BittrexObservable<Response<List<MarketSummary>>>

    @GET("v3/markets/{marketSymbol}/summary")
    fun getMarketSummary(@Path("marketSymbol") symbol: String): BittrexObservable<Response<MarketSummary>>

    @HEAD("v3/markets/summaries")
    fun checkMarketSummaries()

    @GET("v3/markets/tickets")
    fun getTickers(): BittrexObservable<Response<List<Ticker>>>

    @GET("v3/markets/{marketSymbol}/ticker")
    fun getTicker(@Path("marketSymbol") symbol: String): BittrexObservable<Response<Ticker>>

    @HEAD("v3/markets/tickets")
    fun checkTickers()

    @GET("v3/markets/{marketSymbol}/orderbook")
    fun getOrderBook(@Path("marketSymbol") symbol: String, @Query("depth") depth: OrderBookDepth = OrderBookDepth.MID): BittrexObservable<Response<OrderBook>>

    @GET("v3/markets/{marketSymbol}/orderbook")
    fun checkOrderBook(@Path("marketSymbol") symbol: String, @Query("depth") depth: OrderBookDepth = OrderBookDepth.MID)

    @GET("v3/markets/{marketSymbol}/trade")
    fun getTrade(@Path("marketSymbol") symbol: String): BittrexObservable<Response<Trade>>

    @GET("v3/markets/{marketSymbol}/trade")
    fun checkTrade(@Path("marketSymbol") symbol: String): BittrexObservable<Response<Trade>>

    @GET("v3/markets/{marketSymbol}/candles/{candleInterval}/recent")
    fun getRecentCandles(@Path("marketSymbol") symbol: String, @Path("candleInterval") candleInterval: CandleInterval): BittrexObservable<Response<List<Candle>>>

    @GET("v3/markets/{marketSymbol}/candles/{candleInterval}/recent")
    fun checkRecentCandles(@Path("marketSymbol") symbol: String, @Path("candleInterval") candleInterval: CandleInterval)

    @GET("v3/markets/{marketSymbol}/candles/{candleInterval}/historical/{year}/{month}/{day}")
    fun getCandles(@Path("marketSymbol") symbol: String,
                         @Path("candleInterval") candleInterval: CandleInterval,
                         @Path("year") year: Int,
                         @Path("month") month: Int,
                         @Path("day") day: Int): BittrexObservable<Response<List<Candle>>>
}