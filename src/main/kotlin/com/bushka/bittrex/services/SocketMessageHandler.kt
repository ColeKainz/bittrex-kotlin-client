package com.bushka.bittrex.services

import com.bushka.bittrex.model.sockets.*
import com.bushka.bittrex.network.signalr.BittrexSocketClient
import com.bushka.bittrex.network.signalr.DataConverter
import com.google.gson.GsonBuilder
import io.reactivex.subjects.PublishSubject

/**
 * Handles messages from the socket service, each method is called based on the type of
 * message received.
 * @param observables A map of channel name as defined in the Bittrex API docs -> observable
 * the observable is updated with data as messages come in and handlers can be attached to them.
 */
class SocketMessageHandler(
        val observables: MutableMap<String, PublishSubject<Any>>,
        val nonChannelObservables: MutableMap<String, PublishSubject<Any>>,
        val reAuthFunction: () -> Unit
) {

    fun balance(compressedData: String) {
        val deserialized = deserializeMessage<BalanceDelta>(compressedData)
        updateSocketWithData("balance", deserialized)
    }

    fun candle(compressedData: String) {
        val deserialized = deserializeMessage<CandleDelta>(compressedData)
        updateSocketWithData("candle_" + deserialized.marketSymbol, deserialized)
    }

    fun conditional_order(compressedData: String) {
        val deserialized = deserializeMessage<ConditionalOrderDelta>(compressedData)
        updateSocketWithData("conditional_order", deserialized)
    }

    fun deposit(compressedData: String) {
        val deserialized = deserializeMessage<DepositDelta>(compressedData)
        updateSocketWithData("deposit", deserialized)
    }

    fun execution(compressedData: String) {
        val deserialized = deserializeMessage<ExecutionDelta>(compressedData)
        updateSocketWithData("execution", deserialized)
    }

    fun heartbeat(compressedData: String) {
        updateSocketWithData("heartbeat", "heartbeat")
    }

    fun market_summaries(compressedData: String) {
        val deserialized = deserializeMessage<MarketSummaryDelta>(compressedData)
        updateSocketWithData("market_summaries", deserialized)
    }

    fun market_summary(compressedData: String) {
        val deserialized = deserializeMessage<MarketSummary>(compressedData)
        updateSocketWithData("market_summary_" + deserialized.symbol, deserialized)
    }

    fun ticker(compressedData: String) {
        val deserialized = deserializeMessage<Ticker>(compressedData)
        updateSocketWithData("ticker_" + deserialized.symbol, deserialized)
    }

    fun tickers(compressedData: String) {
        val deserialized = deserializeMessage<TickerDelta>(compressedData)
        updateSocketWithData("tickers", deserialized)
    }

    fun authenticationExpiring() {
        val authExpiringObservable = nonChannelObservables["authenticationExpiring"]

        if(authExpiringObservable  == null) {
            reAuthFunction()
        } else {
            authExpiringObservable.onNext("authenticationExpiring")
        }
    }

    private fun updateSocketWithData(channelName: String, data: Any) {
        val observable = observables[channelName]
        observable?.onNext(data)
    }
    private inline fun <reified T> deserializeMessage(compressedData: String): T {
        val convertedData = DataConverter.decodeMessage(compressedData)
        println(convertedData)
        val gson = GsonBuilder().create()
        return gson.fromJson(convertedData, T::class.java)
    }
}