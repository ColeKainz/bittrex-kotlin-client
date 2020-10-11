package com.bushka.bittrex.network.retrofit

import com.bushka.bittrex.network.BittrexObservable
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type

object Rx2ErrorCallAdapterFactory : CallAdapter.Factory() {

    private val original = RxJava2CallAdapterFactory.create()

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *> {
        return RxCallAdapterWrapper(original.get(returnType, annotations, retrofit)!! as CallAdapter<Any, Any>)
    }

    private class RxCallAdapterWrapper(private val wrapped: CallAdapter<Any, Any>) : CallAdapter<Any, BittrexObservable<Any>> {

        override fun responseType(): Type {
            return wrapped.responseType()
        }

        override fun adapt(call: Call<Any>): BittrexObservable<Any> {
            return wrapped.adapt(call) as BittrexObservable<Any>
        }
    }
}