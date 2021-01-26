package com.bushka.bittrex.services

import com.bushka.bittrex.model.conditionalorders.ConditionalOrder
import com.bushka.bittrex.model.conditionalorders.NewConditionalOrder
import com.bushka.bittrex.network.BittrexObservable
import com.bushka.bittrex.network.retrofit.RetrofitFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.*

interface ConditionalOrdersBittrexService {
    companion object {
        fun create(key: String = "", secret: String = "") = create(RetrofitFactory.create(key, secret))
        fun create(retrofit: Retrofit): ConditionalOrdersBittrexService {
            return retrofit.create(ConditionalOrdersBittrexService::class.java)
        }
    }

    @GET("v3/conditional-orders/{conditionalOrderId}")
    fun getConditionalOrder(@Path("conditionalOrderId") id: String): BittrexObservable<Result<ConditionalOrder>>

    @DELETE("v3/conditional-orders/{conditionalOrderId}")
    fun deleteConditionalOrder(@Path("conditionalOrderId") id: String): BittrexObservable<Result<ConditionalOrder>>

    @GET("v3/conditional-orders/closed")
    fun closeConditionalOrder(@Query("marketSymbol") symbol: String? = null,
                              @Query("nextPageToken") nextPageToken: String? = null,
                              @Query("previousPageToken") previousPageToken: String? = null,
                              @Query("pageSize") pageSize: String? = null,
                              @Query("startDate") startDate: String? = null,
                              @Query("endDate") endDate: String): BittrexObservable<Result<ConditionalOrder>>

    @GET("v3/conditional-orders/open")
    fun openConditionalOrder(@Query("marketSymbol") symbol: String? = null): BittrexObservable<Result<ConditionalOrder>>

    @POST("v3/conditional-orders")
    fun postConditionalOrder(@Body newConditionalOrder: NewConditionalOrder): BittrexObservable<Result<ConditionalOrder>>
}