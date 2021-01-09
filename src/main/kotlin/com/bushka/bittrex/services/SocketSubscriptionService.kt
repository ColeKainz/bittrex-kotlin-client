package com.bushka.bittrex.services

import com.bushka.bittrex.model.markets.Ticker
import com.bushka.bittrex.network.BittrexObservable
import com.bushka.bittrex.network.signalr.BittrexSocketClient
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.observables.ConnectableObservable
import io.reactivex.subjects.PublishSubject

/**
 * Observable implementation to handle updates for data.
 */
class BittrexObservableImpl : BittrexObservable<Any>() {
    override fun subscribeActual(observer: Observer<in Any>?) {
        TODO("Not yet implemented")
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
    //TODO Use other bittrex observable
    //TODO visiblity of observables, etc
    var observables = mutableMapOf<String, PublishSubject<Any>>()
    val messageHandler = SocketMessageHandler(observables)
    val socketClient = BittrexSocketClient(apiKey, apiKeySecret, messageHandler)

    /**
     * Subscribes to a specific market's ticker
     * Corresponds to the Ticker channel from the Bittrex API (https://bittrex.github.io/api/v3#method-Ticker)
     * @param symbol String representation of the market that Ticker updates will be listened for (ex: "BTC-USD")
     * @return Observable an observable that will be updated with socket messages
     */
    fun subscribeTicker(symbol: String): Observable<Ticker> {
        val channelName = "ticker_$symbol"

        val observable = PublishSubject.create<Ticker>()
        observables[channelName] = observable as PublishSubject<Any>
        return observable
    }

    /**Todo: Unsubscribe ticker method to remove observable**/

    /**
     * Starts listening to the socket once all the channel subscriptions have been set up.
     */
    fun start() {
        /**TODO: Disallow subscribe if connection exists**/
        val s = socketClient.subscribe(observables.keys.toList())
    }

    fun stop() {
        socketClient.unsubscribe()
    }
}

/**
 * Example
 */
fun main(args: Array<String>) {
    val s = SocketSubscriptionService("", "")
    val tickerObservable = s.subscribeTicker("BTC-USD")
    tickerObservable.subscribe { arg ->
        println(arg)
    }
    s.start()
}