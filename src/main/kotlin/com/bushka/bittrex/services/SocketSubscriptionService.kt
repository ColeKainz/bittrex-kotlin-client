package com.bushka.bittrex.services

import com.bushka.bittrex.model.balances.Balance
import com.bushka.bittrex.model.conditionalorders.ConditionalOrder
import com.bushka.bittrex.model.deposits.Deposit
import com.bushka.bittrex.model.markets.*
import com.bushka.bittrex.model.orders.Execution
import com.bushka.bittrex.model.orders.Order
import com.bushka.bittrex.network.BittrexObservable
import com.bushka.bittrex.network.signalr.BittrexSocketClient
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.subjects.PublishSubject

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
    //TODO visiblity of observables, etc
    private var observables = mutableMapOf<String, PublishSubject<Any>>()
    private val messageHandler = SocketMessageHandler(observables)
    private val socketClient = BittrexSocketClient(apiKey, apiKeySecret, messageHandler)

    fun subscribeBalance(): Observable<Balance> {
        return subscribeChannel<Balance>("balance")
    }

    fun subscribeCandle(symbol: String, interval: String): Observable<Candle> {
        return subscribeChannel<Candle>("candle_$symbol" + "_" + interval)
    }

    fun subscribeConditionalOrder(): Observable<ConditionalOrder> {
        return subscribeChannel<ConditionalOrder>("conditional_order")
    }

    fun subscribeDeposit(): Observable<Deposit> {
        return subscribeChannel<Deposit>("deposit")
    }

    fun subscribeExecution(): Observable<Execution> {
        return subscribeChannel<Execution>("execution")
    }

    fun subscribeHeartbeat(): Observable<Unit> {
        return subscribeChannel<Unit>("heartbeat")
    }

    fun subscribeMarketSummaries(): Observable<MarketSummary> {
        return subscribeChannel<MarketSummary>("market_summaries")
    }

    fun subscribeMarketSummary(symbol: String): Observable<MarketSummary> {
        return subscribeChannel<MarketSummary>("market_summary_$symbol")
    }

    fun subscribeOrder(): Observable<Order> {
        return subscribeChannel<Order>("order")
    }

    fun subscribeOrderBook(symbol: String, depth: Int): Observable<OrderBook> {
        return subscribeChannel<OrderBook>("balance")
    }

    /**
     * Subscribes to a specific market's ticker
     * Corresponds to the Ticker channel from the Bittrex API (https://bittrex.github.io/api/v3#method-Ticker)
     * @param symbol String representation of the market that Ticker updates will be listened for (ex: "BTC-USD")
     * @return Observable an observable that will be updated with socket messages
     */
    fun subscribeTicker(symbol: String): Observable<Ticker> {
        return subscribeChannel<Ticker>("ticker_$symbol")
    }

    fun subscribeTickers(): Observable<Ticker> {
        return subscribeChannel<Ticker>("tickers")
    }

    fun subscribeTrade(symbol: String): Observable<Trade> {
        return subscribeChannel<Trade>("trade_$symbol")
    }

    private fun <T> subscribeChannel(channel: String) : Observable<T> {
        val observable = PublishSubject.create<T>()
        observables[channel] = observable as PublishSubject<Any>
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
    val s = SocketSubscriptionService("13c3f93a297246418b72b7662d0131b1", "adcb4a1e5c854074a1e3fd425808c1f8")
//    val tickerObservable = s.subscribeTicker("BTC-USD")
    val tickerObservable = s.subscribeTickers()
    tickerObservable.subscribe { arg ->
        println(arg)
    }
    s.start()
}