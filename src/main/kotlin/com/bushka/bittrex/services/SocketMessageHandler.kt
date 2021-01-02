package com.bushka.bittrex.services

import com.bushka.bittrex.model.markets.Ticker
import com.bushka.bittrex.network.signalr.DataConverter
import com.google.gson.GsonBuilder

/**
 * Handles messages from the socket service, each method is called based on the type of
 * message received.
 * @param observables A map of channel name as defined in the Bittrex API docs -> observable
 * the observable is updated with data as messages come in and handlers can be attached to them.
 */
class SocketMessageHandler(observables: MutableMap<String, BittrexObservable>) {
    //why did I have to do this?
    val observables = observables

    fun ticker(compressedData: String) {
        var convertedData = DataConverter.decodeMessage(compressedData)
        var gson = GsonBuilder().create()
        var deserialized = gson.fromJson(convertedData, Ticker::class.java)

        //string interpolation derped out here
        val observable = observables.get("ticker_" + deserialized.symbol)
        observable?.update(deserialized)
    }
}