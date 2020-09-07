package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.model.markets.*
import com.cluttered.cryptocurrency.retrofit.BittrexObservable
import com.cluttered.cryptocurrency.retrofit.RetrofitFactory
import retrofit2.Retrofit
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
    fun getMarkets(): BittrexObservable<List<Market>>

    @GET("v3/markets/{marketSymbol}")
    fun getMarket(@Path("marketSymbol") symbol: String): BittrexObservable<Market>

    @GET("v3/markets/summaries")
    fun getMarketSummaries(): BittrexObservable<List<MarketSummary>>

    @GET("v3/markets/{marketSymbol}/summary")
    fun getMarketSummary(@Path("marketSymbol") symbol: String): BittrexObservable<MarketSummary>

    @HEAD("v3/markets/summaries")
    fun checkMarketSummaries()

    @GET("v3/markets/tickets")
    fun getTickers(): BittrexObservable<List<Ticker>>

    @GET("v3/markets/{marketSymbol}/ticker")
    fun getTicker(@Path("marketSymbol") symbol: String): BittrexObservable<Ticker>

    @HEAD("v3/markets/tickets")
    fun checkTickers()

    @GET("v3/markets/{marketSymbol}/orderbook")
    fun getOrderBook(@Path("marketSymbol") symbol: String, @Query("depth") depth: OrderBookDepth = OrderBookDepth.MID): BittrexObservable<OrderBook>

    @GET("v3/markets/{marketSymbol}/orderbook")
    fun checkOrderBook(@Path("marketSymbol") symbol: String, @Query("depth") depth: OrderBookDepth = OrderBookDepth.MID)

    @GET("v3/markets/{marketSymbol}/trade")
    fun getTrade(@Path("marketSymbol") symbol: String): BittrexObservable<Trade>

    @GET("v3/markets/{marketSymbol}/trade")
    fun checkTrade(@Path("marketSymbol") symbol: String): BittrexObservable<Trade>

    @GET("v3/markets/{marketSymbol}/candles/{candleInterval}/recent")
    fun getRecentCandles(@Path("marketSymbol") symbol: String, @Path("candleInterval") candleInterval: CandleInterval): BittrexObservable<List<Candle>>

    @GET("v3/markets/{marketSymbol}/candles/{candleInterval}/recent")
    fun checkRecentCandles(@Path("marketSymbol") symbol: String, @Path("candleInterval") candleInterval: CandleInterval)

    @GET("v3/markets/{marketSymbol}/candles/{candleInterval}/historical/{year}/{month}/{day}")
    fun getCandles(@Path("marketSymbol") symbol: String,
                         @Path("candleInterval") candleInterval: CandleInterval,
                         @Path("year") year: Int,
                         @Path("month") month: Int,
                         @Path("day") day: Int): BittrexObservable<List<Candle>>
}