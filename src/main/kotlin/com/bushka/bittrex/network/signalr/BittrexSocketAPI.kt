package com.bushka.bittrex.network.signalr

import com.bushka.bittrex.credentials.HmacSHA512
import donky.microsoft.aspnet.signalr.client.ConnectionState
import donky.microsoft.aspnet.signalr.client.hubs.HubConnection
import donky.microsoft.aspnet.signalr.client.hubs.HubProxy
import java.util.*

const val URL = "https://socket-v3.bittrex.com/signalr"

const val SIGNATURE_ERROR = "COULD_NOT_CREATE_SIGNATURE"
const val AUTHENTICATION_ERROR = "COULD_NOT_AUTHENTICATE_ACCOUNT"
const val SUBSCRIBE_ERROR = "COULD_NOT_SUBSCRIBE_TO_CHANNELS"
const val UNSUBSCRIBE_ERROR = "COULD_NOT_UNSUBSCRIBE_FROM_CHANNELS"

class SocketResponse(var Success: Boolean, var ErrorCode: String)
class SocketClient {
    private val _hubConnection: HubConnection = HubConnection(URL)
    private val _hubProxy: HubProxy = _hubConnection.createHubProxy("c3")

    fun connect(): Boolean {
        try { _hubConnection.start().get() } catch (e: Exception) {}
        return _hubConnection.state == ConnectionState.Connected
    }

    fun disconnect(): Boolean {
        try { _hubConnection.stop() } catch (e: Exception) {}
        return _hubConnection.state == ConnectionState.Disconnected
    }

    fun setMessageHandler(handler: Any) {
        _hubProxy.subscribe(handler)
    }

    fun authenticate(apiKey: String, apiKeySecret: String): SocketResponse {
        val timestamp = System.currentTimeMillis()
        val randomContent = UUID.randomUUID()
        val content = "$timestamp$randomContent"
        val signedContent = try { createSignature(apiKeySecret, content) }
            catch (e: Exception) { return SocketResponse(false, SIGNATURE_ERROR) }

        return attemptInvocation(AUTHENTICATION_ERROR) {
            _hubProxy.invoke<SocketResponse>(SocketResponse::class.java, "Authenticate", apiKey, timestamp, randomContent, signedContent)
                    .get()
        }
    }

    fun subscribe(channels: List<String>): List<SocketResponse> {
        return attemptInvocationList(SUBSCRIBE_ERROR) {
            _hubProxy
                .invoke(Array<SocketResponse>::class.java, "Subscribe", channels as Any)
                .get()
                .toList()
        }
    }

    fun unsubscribe(channels: List<String>): List<SocketResponse> {
        return attemptInvocationList(UNSUBSCRIBE_ERROR) {
            _hubProxy
                .invoke(Array<SocketResponse>::class.java, "Unsubscribe", channels as Any)
                .get()
                .toList()
        }
    }

    private fun createSignature(apiSecret: String, data: String): String {
        return HmacSHA512(apiSecret).encode(data)
    }

    private fun attemptInvocationList(error: String, block: () -> List<SocketResponse>): List<SocketResponse> {
        return try { block() } catch (e: Exception) { listOf(SocketResponse(false, error)) }
    }

    private fun attemptInvocation(error: String, block: () -> SocketResponse): SocketResponse {
        return try { block() } catch (e: Exception) { SocketResponse(false, error) }
    }
}