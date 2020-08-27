package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.model.address.Address
import com.cluttered.cryptocurrency.model.address.NewAddress
import com.cluttered.cryptocurrency.retrofit.BittrexObservable
import com.cluttered.cryptocurrency.retrofit.RetrofitFactory
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AddressBittrexService {
    companion object {
        fun create(key: String = "", secret: String = "") = create(RetrofitFactory.create())
        fun create(retrofit: Retrofit): AddressBittrexService {
            return retrofit.create(AddressBittrexService::class.java)
        }
    }

    @GET("v3/addresses")
    fun getAddresses(): BittrexObservable<List<Address>>

    @POST("v3/addresses")
    fun putAddresses(@Body address: NewAddress): BittrexObservable<List<Address>>

    @GET("v3/addresses/{symbol}")
    fun getAddresses(@Path("symbol") symbol: String): BittrexObservable<Address>
}