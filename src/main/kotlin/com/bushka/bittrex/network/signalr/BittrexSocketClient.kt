package com.bushka.bittrex.network.signalr

/**
 * Connect to different socket streams
 * Observable(onResponse) subscribe(enum channel) -> sub to channel (eg: balance)
     * basic validation -- disallow multiple subs to channels
 * on(lambda(response)) -> this is where data comes in from channels you're subbed to
 */
class BittrexSocketClient(messageHandler: Any) {
    val socketApi = SocketClient()
    private var apiKey: String? = null
    private var apiKeySecret: String? = null

    constructor(apiKey: String, apiKeySecret: String, messageHandler: Any) : this(messageHandler) {
        this.apiKey = apiKey
        this.apiKeySecret = apiKeySecret
        authenticate()
    }

    init {
        val connectResponse = socketApi.connect()
        if(!connectResponse) {
            throw IllegalStateException("Connection failed")
        }
        socketApi.setMessageHandler(messageHandler)
    }

    fun authenticate() {
        if(this.apiKey == null || this.apiKeySecret == null) {
            throw IllegalStateException("Auth error due to missing api key or api key secret")
        }

        val authResponse = socketApi.authenticate(this.apiKey!!, this.apiKeySecret!!)
        if(!authResponse.Success) {
            throw IllegalStateException("Auth error due to ${authResponse.ErrorCode}")
        }
    }

    fun subscribe(channels: List<String>): List<SocketResponse> {
        return socketApi.subscribe(channels)
    }

    fun unsubscribe(channels: List<String>): List<SocketResponse> {
        return socketApi.unsubscribe(channels)
    }

    fun disconnect(): Boolean {
        return socketApi.disconnect()
    }
}
