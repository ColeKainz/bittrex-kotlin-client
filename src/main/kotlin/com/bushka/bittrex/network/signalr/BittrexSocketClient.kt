package com.bushka.bittrex.network.signalr

import com.bushka.bittrex.model.balances.Balance
import com.bushka.bittrex.model.markets.Ticker
import com.google.gson.GsonBuilder
import sun.plugin.dom.exception.InvalidStateException
import kotlin.properties.Delegates

//fun main(args: Array<String>) {
//    val socketClient = BittrexSocketClient("13c3f93a297246418b72b7662d0131b1", "adcb4a1e5c854074a1e3fd425808c1f8")
//    socketClient.subscribe(listOf("ticker_BTC-USD"))
//    readLine()
//}

/**
 * Connect to different socket streams
 * Observable(onResponse) subscribe(enum channel) -> sub to channel (eg: balance)
     * basic validation -- disallow multiple subs to channels
 * on(lambda(response)) -> this is where data comes in from channels you're subbed to
 */
class BittrexSocketClient(apiKey: String, apiKeySecret: String, messageHandler: Any) {
    val socketApi = SocketClient()

    init {
        val connectResponse = socketApi.connect()
        if(!connectResponse) {
            throw InvalidStateException("Connection failed")
        }

        val authResponse = socketApi.authenticate(apiKey, apiKeySecret)
        if(!authResponse.Success) {
            throw InvalidStateException("Auth error due to ${authResponse.ErrorCode}")
        }
        socketApi.setMessageHandler(messageHandler)
    }

    fun subscribe(channels: List<String>): List<SocketResponse> {
        return socketApi.subscribe(channels)
    }

    fun unsubscribe(): Boolean {
        return socketApi.disconnect()
    }
}
