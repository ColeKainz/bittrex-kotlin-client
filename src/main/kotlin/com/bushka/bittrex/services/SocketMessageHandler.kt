package com.bushka.bittrex.services

import com.bushka.bittrex.model.balances.Balance
import com.bushka.bittrex.model.markets.Candle
import com.bushka.bittrex.model.markets.Ticker
import com.bushka.bittrex.network.BittrexObservable
import com.bushka.bittrex.network.signalr.DataConverter
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Handles messages from the socket service, each method is called based on the type of
 * message received.
 * @param observables A map of channel name as defined in the Bittrex API docs -> observable
 * the observable is updated with data as messages come in and handlers can be attached to them.
 */
class SocketMessageHandler(val observables: MutableMap<String, PublishSubject<Any>>) {

    fun balance(compressedData: String) {
        val deserialized = deserializeMessage<Balance>(compressedData)
        val observable = observables["balance"]
        observable?.onNext(deserialized)
    }

    fun candle(compressedData: String) {
        val deserialized = deserializeMessage<Candle>(compressedData)
        //TODO: doesn't deserialize to a candle
        val observable = observables["candle_" + deserialized]
        observable?.onNext(deserialized)
    }

    fun conditional_order(compressedData: String) {
        val deserialized = deserializeMessage<Candle>(compressedData)
        val observable = observables["conditional_order"]
        observable?.onNext(deserialized)
    }

    fun deposit(compressedData: String) {
        val deserialized = deserializeMessage<Candle>(compressedData)
        val observable = observables["deposit"]
        observable?.onNext(deserialized)
    }

    fun execution(compressedData: String) {
        val deserialized = deserializeMessage<Candle>(compressedData)
        val observable = observables["execution"]
        observable?.onNext(deserialized)
    }

    fun heartbeat(compressedData: String) {
        val deserialized = deserializeMessage<Candle>(compressedData)
        val observable = observables["execution"]
        observable?.onNext(deserialized)
    }

    fun market_summaries(compressedData: String) {
        val deserialized = deserializeMessage<Candle>(compressedData)
        val observable = observables["execution"]
        observable?.onNext(deserialized)
    }

    fun market_summary(compressedData: String) {
        val deserialized = deserializeMessage<Candle>(compressedData)
        val observable = observables["execution"]
        observable?.onNext(deserialized)
    }

    fun ticker(compressedData: String) {
        val deserialized = deserializeMessage<Ticker>(compressedData)
        val observable = observables["ticker_" + deserialized.symbol]
        observable?.onNext(deserialized)
    }

    fun tickers(compressedData: String) {
        val deserialized = deserializeMessage<Ticker>(compressedData)
        //TODO: doesn't deserialize to a ticker
        val observable = observables["tickers"]
        observable?.onNext(deserialized)
    }

    private inline fun <reified T> deserializeMessage(compressedData: String): T {
        val convertedData = DataConverter.decodeMessage(compressedData)
        println(convertedData)
        val gson = GsonBuilder().create()
        return gson.fromJson(convertedData, T::class.java)
    }
}