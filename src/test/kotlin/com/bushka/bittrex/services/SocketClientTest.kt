package com.bushka.bittrex.services

import com.bushka.bittrex.network.signalr.SocketClient
import com.bushka.bittrex.network.signalr.DataConverter
import com.google.gson.GsonBuilder
import org.junit.Test
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class SocketClientTest {
    val API_KEY = ""
    val API_SECRET = ""

    @Test
    fun TestSocketClient() {
        val client = SocketClient(URL)
        if (!connect(client)) {
            throw Exception("Could not connect to server")
        }
        if (!API_SECRET.isEmpty()) {
            authenticateClient(client)
        } else {
            println("Authentication skipped because API key was not provided")
        }
        subscribe(client)
    }

    fun connect(client: SocketClient): Boolean {
        var connected = client.connect()

        if (connected) {
            println("Connected")
        } else {
            println("Failed to connect")
        }

        return connected
    }

    fun authenticateClient(client: SocketClient) {
        if (authenticate(client, API_KEY, API_SECRET)) {
            val scheduler = Executors.newScheduledThreadPool(1)
            val authExpiringHandler: Any = object : Any() {
                fun authenticationExpiring() {
                    println("Authentication expiring...")
                    scheduler.schedule({ authenticate(client, API_KEY, API_SECRET) }, 1, TimeUnit.SECONDS)
                }
            }
            client.setMessageHandler(authExpiringHandler)
        }
    }

    fun authenticate(client: SocketClient, apiKey: String, apiSecret: String): Boolean {
        val response = client.authenticate(apiKey, apiSecret)
        if (response.Success) {
            println("Authenticated")
        } else {
            println("Failed to authenticate: " + response.ErrorCode)
        }

        return response.Success
    }

    fun subscribe(client: SocketClient) {
        val channels = listOf("heartbeat", "trade_BTC-USD", "balance")
        val msgHandler: Any = object : Any() {
            fun heartbeat() {
                println("<heartbeat>")
            }

            fun trade(compressedData: String) {
                // If subscribed to multiple market's trade streams,
                // use the marketSymbol field in the message to differentiate
                printSocketMessage("Trade", compressedData)
            }

            fun balance(compressedData: String) {
                printSocketMessage("Balance", compressedData)
            }
        }

        client.setMessageHandler(msgHandler)
        val response = client.subscribe(channels)
        for (i in channels.indices) {
            println(channels[i] + ": " + if (response[i].Success) "Success" else response[i].ErrorCode)
        }
    }
}

fun printSocketMessage(msgType: String, compressedData: String) {
    var text = ""
    text = try {
        val msg = DataConverter.decodeMessage(compressedData)
        val gson = GsonBuilder().setPrettyPrinting().create()
        gson.toJson(msg)
    } catch (e: Exception) {
        "Error decompressing message - $e - $compressedData"
    }
    println("$msgType: $text")
}