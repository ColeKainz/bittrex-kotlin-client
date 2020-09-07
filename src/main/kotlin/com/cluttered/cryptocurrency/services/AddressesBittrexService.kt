package com.cluttered.cryptocurrency.services

import com.cluttered.cryptocurrency.model.addresses.Address
import com.cluttered.cryptocurrency.model.addresses.NewAddress
import com.cluttered.cryptocurrency.retrofit.BittrexObservable
import com.cluttered.cryptocurrency.retrofit.RetrofitFactory
import retrofit2.Retrofit
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
    fun getAddresses(): BittrexObservable<List<Address>>

    @POST("v3/addresses")
    fun putAddresses(@Body address: NewAddress): BittrexObservable<List<Address>>

    @GET("v3/addresses/{symbol}")
    fun getAddresses(@Path("symbol") symbol: String): BittrexObservable<Address>
}