package com.bushka.bittrex.services

import com.bushka.bittrex.model.sockets.MarketSummary
import com.bushka.bittrex.model.sockets.Ticker
import com.bushka.bittrex.model.sockets.*
import com.bushka.bittrex.network.signalr.BittrexSocketClient
import io.reactivex.Observable
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
class SocketSubscriptionService() {
    //TODO visiblity of observables, etc
    private var observables = mutableMapOf<String, PublishSubject<Any>>()
    private val messageHandler = SocketMessageHandler(observables)
    private var socketClient: BittrexSocketClient

    constructor(apiKey: String, apiKeySecret: String) : this() {
        socketClient = BittrexSocketClient(apiKey, apiKeySecret, messageHandler)
    }

    init {
        socketClient = BittrexSocketClient(messageHandler)
    }

    fun subscribeBalance(): Observable<BalanceDelta> {
        return subscribeChannel<BalanceDelta>("balance")
    }

    fun subscribeCandle(symbol: String, interval: String): Observable<CandleDelta> {
        return subscribeChannel<CandleDelta>("candle_$symbol" + "_" + interval)
    }

    fun subscribeConditionalOrder(): Observable<ConditionalOrderDelta> {
        return subscribeChannel<ConditionalOrderDelta>("conditional_order")
    }

    fun subscribeDeposit(): Observable<DepositDelta> {
        return subscribeChannel<DepositDelta>("deposit")
    }

    fun subscribeExecution(): Observable<ExecutionDelta> {
        return subscribeChannel<ExecutionDelta>("execution")
    }

    fun subscribeHeartbeat(): Observable<String> {
        return subscribeChannel<String>("heartbeat")
    }

    fun subscribeMarketSummaries(): Observable<MarketSummaryDelta> {
        return subscribeChannel<MarketSummaryDelta>("market_summaries")
    }

    fun subscribeMarketSummary(symbol: String): Observable<MarketSummary> {
        return subscribeChannel<MarketSummary>("market_summary_$symbol")
    }

    fun subscribeOrder(): Observable<OrderDelta> {
        return subscribeChannel<OrderDelta>("order")
    }

    fun subscribeOrderBook(symbol: String, depth: Int): Observable<OrderbookDelta> {
        return subscribeChannel<OrderbookDelta>("balance")
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

    fun subscribeTickers(): Observable<TickerDelta> {
        return subscribeChannel<TickerDelta>("tickers")
    }

    fun subscribeTrade(symbol: String): Observable<TradeDelta> {
        return subscribeChannel<TradeDelta>("trade_$symbol")
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
    val s = SocketSubscriptionService()
    val tickerObservable = s.subscribeTickers()
    tickerObservable.subscribe { arg ->
        println(arg)
    }
    s.start()
}