package com.bushka.bittrex.services

import com.bushka.bittrex.model.withdrawals.NewWithdrawal
import com.bushka.bittrex.model.withdrawals.WhiteListAddress
import com.bushka.bittrex.model.withdrawals.Withdrawal
import com.bushka.bittrex.model.withdrawals.WithdrawalStatus
import com.bushka.bittrex.retrofit.BittrexObservable
import com.bushka.bittrex.retrofit.RetrofitFactory
import retrofit2.Retrofit
import retrofit2.http.*

interface WithdrawalBittrexService {
    companion object {
        fun create(key: String = "", secret: String = "") = create(RetrofitFactory.create(key, secret))
        fun create(retrofit: Retrofit): WithdrawalBittrexService {
            return retrofit.create(WithdrawalBittrexService::class.java)
        }
    }

    @GET("v3/withdrawals/open")
    fun getOpenWithdrawals(@Query("status") status: WithdrawalStatus? = null, @Query("currencySymbol") symbol: String? = null): BittrexObservable<List<Withdrawal>>

    @GET("v3/withdrawals/{withdrawalId}")
    fun getWithdrawal(@Path("withdrawalId") withdrawalId: String): BittrexObservable<Withdrawal>

    @GET("v3/withdrawals/ByTxId/{txId}")
    fun getWithdrawals(@Path("txId") txId: String): BittrexObservable<List<Withdrawal>>

    @DELETE("v3/withdrawals/{withdrawalId}")
    fun deleteWithdrawal(@Path("withdrawalId") withdrawalId: String): BittrexObservable<Withdrawal>

    @POST("v3/withdrawals/")
    fun postWithdrawal(@Body withdrawal: NewWithdrawal): BittrexObservable<Withdrawal>

    @GET("v3/withdrawals/closed")
    fun getClosedWithdrawals(@Query("status") status: WithdrawalStatus? = null,
                        @Query("currencySymbol") symbol: String? = null,
                        @Query("nextPageToken") nextPageToken: String? = null,
                        @Query("previousPageToken") previousPageToken: String? = null,
                        @Query("pageSize") pageSize: String? = null,
                        @Query("startDate") startDate: String? = null,
                        @Query("endDate") endDate: String? = null): BittrexObservable<List<Withdrawal>>

    @GET("v3/withdrawals/whitelistAddresses")
    fun getWhiteListedAddresses(): BittrexObservable<WhiteListAddress>
}