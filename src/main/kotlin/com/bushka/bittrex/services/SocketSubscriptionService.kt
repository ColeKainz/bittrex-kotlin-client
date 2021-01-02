package com.bushka.bittrex.services

import com.bushka.bittrex.network.signalr.BittrexSocketClient
import java.util.*

/**
 * Observable implementation to handle updates for data.
 */
class BittrexObservable : Observable() {
    fun update(newData: Any) {
        setChanged()
        notifyObservers(newData)
    }
}

/**
 * Handles subscribing to sockets and returns observables that can be used to watch for changes.
 * Usage:
 *  - Call all the subscribe[*]() functions for the sockets that you would like to subscribe to
 *  - Attach handlers to the Observable returned to handle updates
 *  - Call the start() function to start listening to the socket
 *
 *  @param apiKey Bittrex API Key
 *  @param apiKeySecret Bittrex API Secret
 */
class SocketSubscriptionService(apiKey: String, apiKeySecret: String) {
    var channels = mutableListOf<String>()
    var observables = mutableMapOf<String, BittrexObservable>()
    val messageHandler = SocketMessageHandler(observables)
    val socketClient = BittrexSocketClient(apiKey, apiKeySecret, messageHandler)

    /**
     * Subscribes to a specific market's ticker
     * Corresponds to the Ticker channel from the Bittrex API (https://bittrex.github.io/api/v3#method-Ticker)
     * @param symbol String representation of the market that Ticker updates will be listened for (ex: "BTC-USD")
     * @return Observable an observable that will be updated with socket messages
     */
    fun subscribeTicker(symbol: String): Observable {
        val channelName = "ticker_$symbol"
        channels.add(channelName)

        val observable = BittrexObservable()
        observables[channelName] = observable
        return observable
    }

    /**
     * Starts listening to the socket once all the channel subscriptions have been set up.
     */
    fun start() {
        socketClient.subscribe(channels);
    }
}

/**
 * Example
 */
fun main(args: Array<String>) {
    val s = SocketSubscriptionService("apiKey", "apiKeySecret")
    val tickerObservable = s.subscribeTicker("BTC-USD")
    tickerObservable.addObserver { o, arg ->
        println(o)
        println(arg)
    }
    s.start()
}