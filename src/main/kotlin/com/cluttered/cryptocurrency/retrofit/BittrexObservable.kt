package com.cluttered.cryptocurrency.retrofit

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import retrofit2.HttpException

/**
 * Make the Observable look more like a promise.
 */
typealias BittrexObservable<T> = Observable<T>

fun <T> BittrexObservable<T>.onFailure(callback: (HttpException) -> Unit): BittrexObservable<T> {
    return this.doOnError {
        it as HttpException
        callback(it)
    }
}

fun <T> BittrexObservable<T>.onSuccess(callback: (T) -> Unit): Disposable {
    return this.subscribe {
        callback(it)
    }
}