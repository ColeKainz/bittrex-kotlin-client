package com.bushka.bittrex.credentials

import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import kotlin.math.sign

class ApiSignInterceptor(private val key: String, secret: String) : Interceptor {

    private val hmacSHA512 = HmacSHA512(secret)

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url()

        // Ignore if using public api
        if (url.encodedPath().contains("public"))
            return chain.proceed(chain.request())

        val currentMillis = System.currentTimeMillis()
        val body = if(chain.request().body() == null) {
            ""
        } else {
            //TODO: This doesn't work
            //We need chain.request().body().content to be visible
            chain.request().body().toString()
        }

        val hashedBody = MacSHA512.hash(body)
        val apiSignature = currentMillis.toString() + chain.request().url() + chain.request().method() + hashedBody
        val signedApiSignature = hmacSHA512.encode(apiSignature)


        val request = chain.request().newBuilder()
                .url(url)
                .addHeader("Api-Key", key)
                .addHeader("Api-Timestamp", currentMillis.toString())
                .addHeader("Api-Content-Hash", hashedBody)
                .addHeader("Api-Signature", signedApiSignature)
                .build()
        return chain.proceed(request)
    }
}