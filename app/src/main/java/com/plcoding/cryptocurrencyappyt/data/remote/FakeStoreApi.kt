package com.plcoding.cryptocurrencyappyt.data.remote

import com.plcoding.cryptocurrencyappyt.data.remote.dto.ProductDTo
import retrofit2.http.GET
import retrofit2.http.Query

interface FakeStoreApi {
    @GET("products")
    suspend fun getFakeProducts(@Query("limit") limit: Int): List<ProductDTo>
}