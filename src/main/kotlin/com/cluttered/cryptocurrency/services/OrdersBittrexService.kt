package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.model.conditionalorders.NewOrder
import com.cluttered.cryptocurrency.model.deposits.DepositStatus
import com.cluttered.cryptocurrency.model.orders.Execution
import com.cluttered.cryptocurrency.model.orders.Order
import com.cluttered.cryptocurrency.retrofit.BittrexObservable
import com.cluttered.cryptocurrency.retrofit.RetrofitFactory
import retrofit2.Retrofit
import retrofit2.http.*

interface OrdersBittrexService {
    companion object {
        fun create(key: String = "", secret: String = "") = create(RetrofitFactory.create(key, secret))
        fun create(retrofit: Retrofit): OrdersBittrexService {
            return retrofit.create(OrdersBittrexService::class.java)
        }
    }

    @GET("v3/orders/open")
    fun getOpenOrders(@Query("marketSymbol") symbol: String? = null): BittrexObservable<List<Order>>

    @HEAD("v3/orders/open")
    fun checkOpenOrders()

    @GET("v3/orders/{orderId}")
    fun getOrder(@Path("orderId") orderId: String): BittrexObservable<Order>

    @DELETE("v3/orders/{orderId}")
    fun deleteOrder(@Path("orderId") orderId: String): BittrexObservable<Order>

    @GET("v3/orders/closed")
    fun getClosedOrders(@Query("status") status: DepositStatus? = null,
                        @Query("marketSymbol") symbol: String? = null,
                        @Query("nextPageToken") nextPageToken: String? = null,
                        @Query("previousPageToken") previousPageToken: String? = null,
                        @Query("pageSize") pageSize: String? = null,
                        @Query("startDate") startDate: String? = null,
                        @Query("endDate") endDate: String? = null): BittrexObservable<List<Order>>

    @GET("v3/orders/{orderId}/executions")
    fun getExecutions(@Path("orderId") orderId: String): BittrexObservable<List<Execution>>

    @POST("v3/orders/")
    fun postOrder(@Body order: NewOrder): BittrexObservable<Order>
}