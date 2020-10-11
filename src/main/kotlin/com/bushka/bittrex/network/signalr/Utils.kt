package com.bushka.bittrex.network.signalr

import java.io.Closeable

fun <R> use(close: () -> Unit, block: (Closeable) -> R): R {
    return Closeable(close).use(block)
}