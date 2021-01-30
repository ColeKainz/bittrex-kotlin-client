package com.bushka.bittrex.services

import com.bushka.bittrex.model.addresses.Address
import com.bushka.bittrex.model.addresses.NewAddress
import com.bushka.bittrex.network.BittrexObservable
import com.bushka.bittrex.network.retrofit.RetrofitFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AddressesBittrexService {
    companion object {
        fun create(key: String = "", secret: String = "") = create(RetrofitFactory.create(key, secret))
        fun create(retrofit: Retrofit): AddressesBittrexService {
            return retrofit.create(AddressesBittrexService::class.java)
        }
    }

    @GET("v3/addresses")
    fun getAddresses(): BittrexObservable<Response<List<Address>>>

    @POST("v3/addresses")
    fun putAddresses(@Body address: NewAddress): BittrexObservable<Response<List<Address>>>

    @GET("v3/addresses/{symbol}")
    fun getAddresses(@Path("symbol") symbol: String): BittrexObservable<Response<Address>>
}