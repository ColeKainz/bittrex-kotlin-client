package com.bushka.bittrex.network.signalr

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.util.*
import java.util.zip.DataFormatException
import java.util.zip.Inflater

object DataConverter {
    @Throws(DataFormatException::class, UnsupportedEncodingException::class)
    fun decodeMessage(encodedData: String): JsonObject {
        val compressedData = Base64.getDecoder().decode(encodedData)

        val jsonString = Inflater(true).run {
            setInput(compressedData)
            getJsonString(this)
        }

        return JsonParser().parse(jsonString).asJsonObject
    }

    private fun getJsonString(inflater: Inflater): String {
        return use(inflater::end) {
            var buffer = ByteArray(1024)
            val resultBuilder = StringBuilder()
            while (inflater.inflate(buffer) > 0) {
                resultBuilder.append(String(buffer, Charset.defaultCharset()))
                buffer = ByteArray(1024)
            }

            return@use resultBuilder.toString().trim { it <= ' ' }
        }
    }
}