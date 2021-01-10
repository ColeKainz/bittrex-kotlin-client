package com.bushka.bittrex.network.signalr

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
            throw IllegalStateException("Connection failed")
        }

        val authResponse = socketApi.authenticate(apiKey, apiKeySecret)
        if(!authResponse.Success) {
            throw IllegalStateException("Auth error due to ${authResponse.ErrorCode}")
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
