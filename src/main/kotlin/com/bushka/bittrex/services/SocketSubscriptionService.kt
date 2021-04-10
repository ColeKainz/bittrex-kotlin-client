package com.bushka.bittrex.services

import com.bushka.bittrex.BittrexClient
import com.bushka.bittrex.model.sockets.MarketSummary
import com.bushka.bittrex.model.sockets.Ticker
import com.bushka.bittrex.model.sockets.*
import com.bushka.bittrex.model.withdrawals.NewWithdrawal
import com.bushka.bittrex.network.signalr.BittrexSocketClient
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.math.BigDecimal

/**
 * Handles subscribing to sockets and returns observables that can be used to watch for changes.
 * Usage:
 *  - Call all the subscribe[*]() functions for the sockets that you would like to subscribe to
 *  - Attach handlers to the Observable returned to handle updates
 *  - Call the start() function to start listening to the socket
 * Default constructor will only allow for subscriptions to sockets that do not require authentication.
 * Use the secondary constructor for authenticated calls
 */
class SocketSubscriptionService() {
    private var observables = mutableMapOf<String, PublishSubject<Any>>()
    private var nonChannelObservables = mutableMapOf<String, PublishSubject<Any>>()
    private var socketClient: BittrexSocketClient
    private val messageHandler = SocketMessageHandler(observables, nonChannelObservables, { this.reAuthenticate() })

    /**
     * Alternative constructor that allows for authenticated socket subscriptions.
     *  @param {string} apiKey Bittrex API Key
     *  @param {string} apiKeySecret Bittrex API Secret
     */
    constructor(apiKey: String, apiKeySecret: String) : this() {
        socketClient = BittrexSocketClient(apiKey, apiKeySecret, messageHandler)
    }

    init {
        socketClient = BittrexSocketClient(messageHandler)
    }

    /**
     * Subscribes to the balance socket
     * Corresponds to the Balance channel from the Bittrex API (https://bittrex.github.io/api/v3#method-Balance)
     * @return {Observable} An observable that will be updated with socket messages
     */
    fun subscribeBalance(): Observable<BalanceDelta> {
        return subscribeChannel<BalanceDelta>("balance")
    }

    /**
     * Unsubscribes to the balance socket
     */
    fun unsubscribeBalance(): Unit {
        return unsubscribeChannel("balance")
    }

    /**
     * Subscribes to the Candle socket
     * Corresponds to the Candle channel from the Bittrex API (https://bittrex.github.io/api/v3#method-Candle)
     * @param {String} marketSymbol representation of the market that Candle updates will be listened for (ex: "BTC-USD")
     * @param {String} interval desired time interval between candles. Possible values:
     * [MINUTE_1, MINUTE_5, HOUR_1, DAY_1]
     * @return {Observable} An observable that will be updated with socket messages
     */
    fun subscribeCandle(marketSymbol: String, interval: String): Observable<CandleDelta> {
        return subscribeChannel<CandleDelta>("candle_$marketSymbol" + "_" + interval)
    }

    /**
     * Unsubscribes to the Candle socket
     */
    fun unsubscribeCandle(marketSymbol: String, interval: String): Unit {
        unsubscribeChannel("candle_$marketSymbol" + "_" + interval)
    }

    /**
     * Subscribes to the Conditional Order socket
     * Corresponds to the Conditional Order channel from the Bittrex API (https://bittrex.github.io/api/v3#method-Conditional-Order)
     * @return {Observable} An observable that will be updated with socket messages
     */
    fun subscribeConditionalOrder(): Observable<ConditionalOrderDelta> {
        return subscribeChannel<ConditionalOrderDelta>("conditional_order")
    }

    /**
     * Unsubscribes to the Conditional Order socket
     */
    fun unsubscribeConditionalOrder(): Unit {
        unsubscribeChannel("conditional_order")
    }

    /**
     * Subscribes to the Deposit socket
     * Corresponds to the Deposit channel from the Bittrex API (https://bittrex.github.io/api/v3#method-Deposit)
     * @return {Observable} An observable that will be updated with socket messages
     */
    fun subscribeDeposit(): Observable<DepositDelta> {
        return subscribeChannel<DepositDelta>("deposit")
    }

    /**
     * Unsubscribes to the Deposit socket
     */
    fun unsubscribeDeposit(): Unit {
        unsubscribeChannel("deposit")
    }

    /**
     * Subscribes to the Execution socket
     * Corresponds to the Execution channel from the Bittrex API (https://bittrex.github.io/api/v3#method-Execution)
     * @return {Observable} An observable that will be updated with socket messages
     */
    fun subscribeExecution(): Observable<ExecutionDelta> {
        return subscribeChannel<ExecutionDelta>("execution")
    }

    /**
     * Unsubscribes to the Execution socket
     */
    fun unsubscribeExecution(): Unit {
        unsubscribeChannel("execution")
    }

    /**
     * Subscribes to the Heartbeat socket
     * Corresponds to the Heartbeat channel from the Bittrex API (https://bittrex.github.io/api/v3#method-Heartbeat)
     * @return {Observable} An observable that will be updated with the static text "hearbeat" whenever a message is received
     */
    fun subscribeHeartbeat(): Observable<String> {
        return subscribeChannel<String>("heartbeat")
    }

    /**
     * Unsubscribes to the Heartbeat socket
     */
    fun unsubscribeHeartbeat(): Unit {
        unsubscribeChannel("heartbeat")
    }

    /**
     * Subscribes to the Market Summaries socket
     * Corresponds to the Market Summaries channel from the Bittrex API (https://bittrex.github.io/api/v3#method-Market-Summaries)
     * @return {Observable} An observable that will be updated with socket messages
     */
    fun subscribeMarketSummaries(): Observable<MarketSummaryDelta> {
        return subscribeChannel<MarketSummaryDelta>("market_summaries")
    }

    /**
     * Unsubscribes to the Market Summaries socket
     */
    fun unsubscribeMarketSummaries(): Unit {
        unsubscribeChannel("market_summaries")
    }

    /**
     * Subscribes to the Market Summary socket
     * Corresponds to the Market Summary channel from the Bittrex API (https://bittrex.github.io/api/v3#method-Market-Summary)
     * @param {String} marketSymbol representation of the market that Market Summary updates will be listened for (ex: "BTC-USD")
     * @return {Observable} An observable that will be updated with socket messages
     */
    fun subscribeMarketSummary(marketSymbol: String): Observable<MarketSummary> {
        return subscribeChannel<MarketSummary>("market_summary_$marketSymbol")
    }

    /**
     * Unsubscribes to the Market Summary socket
     */
    fun unsubscribeMarketSummary(marketSymbol: String): Unit {
        unsubscribeChannel("market_summary_$marketSymbol")
    }

    /**
     * Subscribes to the Order socket
     * Corresponds to the Order channel from the Bittrex API (https://bittrex.github.io/api/v3#method-Order)
     * @return {Observable} An observable that will be updated with socket messages
     */
    fun subscribeOrder(): Observable<OrderDelta> {
        return subscribeChannel<OrderDelta>("order")
    }

    /**
     * Unsubscribes to the Order socket
     */
    fun unsubscribeOrder(): Unit {
        unsubscribeChannel("order")
    }

    /**
     * Subscribes to the Order Book socket
     * Corresponds to the Order Book channel from the Bittrex API (https://bittrex.github.io/api/v3#method-Order-Book)
     * @param {String} marketSymbol representation of the market that Order Book updates will be listened for (ex: "BTC-USD")
     * @param {String} depth depth of the Order Book to monitor. Possible values: [1, 25, 500]
     * @return {Observable} An observable that will be updated with socket messages
     */
    fun subscribeOrderBook(marketSymbol: String, depth: String): Observable<OrderbookDelta> {
        return subscribeChannel<OrderbookDelta>("orderbook_$marketSymbol" + "_$depth")
    }

    /**
     * Unsubscribes to the Order Book socket
     */
    fun unsubscribeOrderBook(marketSymbol: String, depth: String): Unit {
        unsubscribeChannel("orderbook_$marketSymbol" + "_$depth")
    }

    /**
     * Subscribes to a specific market's ticker
     * Corresponds to the Ticker channel from the Bittrex API (https://bittrex.github.io/api/v3#method-Ticker)
     * @param {String} marketSymbol representation of the market that Ticker updates will be listened for (ex: "BTC-USD")
     * @return {Observable} an observable that will be updated with socket messages
     */
    fun subscribeTicker(marketSymbol: String): Observable<Ticker> {
        return subscribeChannel<Ticker>("ticker_$marketSymbol")
    }


    /**
     * Unsubscribes to a specific market's ticker
     */
    fun unsubscribeTicker(marketSymbol: String): Unit {
        unsubscribeChannel("ticker_$marketSymbol")
    }

    /**
     * Subscribes to the Tickers socket
     * Corresponds to the Tickers channel from the Bittrex API (https://bittrex.github.io/api/v3#method-Tickers)
     * @return {Observable} An observable that will be updated with socket messages
     */
    fun subscribeTickers(): Observable<TickerDelta> {
        return subscribeChannel<TickerDelta>("tickers")
    }

    /**
     * Unsubscribes to the Tickers socket
     */
    fun unsubscribeTickers(): Unit {
        unsubscribeChannel("tickers")
    }

    /**
     * Subscribes to the Trade socket
     * Corresponds to the Trade channel from the Bittrex API (https://bittrex.github.io/api/v3#method-Trade)
     * @param {String} marketSymbol representation of the market that Trade updates will be listened for (ex: "BTC-USD")
     * @return {Observable} An observable that will be updated with socket messages
     */
    fun subscribeTrade(marketSymbol: String): Observable<TradeDelta> {
        return subscribeChannel<TradeDelta>("trade_$marketSymbol")
    }

    /**
     * Unsubscribes to the Trade socket
     * Corresponds to the Trade channel from the Bittrex API (https://bittrex.github.io/api/v3#method-Trade)
     */
    fun unsubscribeTrade(marketSymbol: String): Unit {
        unsubscribeChannel("trade_$marketSymbol")
    }

    /**
     * Creates a subscription to the authentication expiration message.
     * This is an optional subscription that provides the option of implementing custom reauth logic,
     * if there are no subscribers on this observable, default re-authentication logic is run.
     */
    fun subscribeAuthenticationExpiring(): Observable<String> {
        val observable = PublishSubject.create<String>()
        nonChannelObservables["authenticationExpiring"] = observable as PublishSubject<Any>
        return observable
    }

    /**
     * Unsubscribes to the authentication expiration message.
     */
    fun unsubscribeAuthenticationExpiring(): Unit {
        unsubscribeChannel("authenticationExpiring")
    }

    /**
     * Re-authenticates the socket connection. Can be called if manually handling re-authentication, otherwise
     * called by default when auth expires.
     */
    fun reAuthenticate() {
        this.socketClient.authenticate()
    }

    private fun <T> subscribeChannel(channel: String) : Observable<T> {
        val observable = PublishSubject.create<T>()
        observables[channel] = observable as PublishSubject<Any>
        return observable
    }


    private fun unsubscribeChannel(channel: String) : Unit {
        socketClient.unsubscribe(listOf(channel))
        observables.remove(channel)
    }

    /**
     * Starts listening to the socket once all the channel subscriptions have been set up.
     * This method *must* be called to start receiving messages
     */
    fun start() {
        val s = socketClient.subscribe(observables.keys.toList())
    }

    fun stop() {
        socketClient.disconnect()
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
        s.unsubscribeTickers()
    }
    s.start()


}