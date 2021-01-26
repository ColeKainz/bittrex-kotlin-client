package com.bushka.bittrex.services

import com.bushka.bittrex.model.conditionalorders.NewOrder
import com.bushka.bittrex.model.deposits.DepositStatus
import com.bushka.bittrex.model.orders.Execution
import com.bushka.bittrex.model.orders.Order
import com.bushka.bittrex.network.BittrexObservable
import com.bushka.bittrex.network.retrofit.RetrofitFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.*

interface OrdersBittrexService {
    companion object {
        fun create(key: String = "", secret: String = "") = create(RetrofitFactory.create(key, secret))
        fun create(retrofit: Retrofit): OrdersBittrexService {
            return retrofit.create(OrdersBittrexService::class.java)
        }
    }

    @GET("v3/orders/open")
    fun getOpenOrders(@Query("marketSymbol") symbol: String? = null): BittrexObservable<Result<List<Order>>>

    @HEAD("v3/orders/open")
    fun checkOpenOrders()

    @GET("v3/orders/{orderId}")
    fun getOrder(@Path("orderId") orderId: String): BittrexObservable<Result<Order>>

    @DELETE("v3/orders/{orderId}")
    fun deleteOrder(@Path("orderId") orderId: String): BittrexObservable<Result<Order>>

    @GET("v3/orders/closed")
    fun getClosedOrders(@Query("status") status: DepositStatus? = null,
                        @Query("marketSymbol") symbol: String? = null,
                        @Query("nextPageToken") nextPageToken: String? = null,
                        @Query("previousPageToken") previousPageToken: String? = null,
                        @Query("pageSize") pageSize: String? = null,
                        @Query("startDate") startDate: String? = null,
                        @Query("endDate") endDate: String? = null): BittrexObservable<Result<List<Order>>>

    @GET("v3/orders/{orderId}/executions")
    fun getExecutions(@Path("orderId") orderId: String): BittrexObservable<Result<List<Execution>>>

    @POST("v3/orders/")
    fun postOrder(@Body order: NewOrder): BittrexObservable<Result<Order>>
}