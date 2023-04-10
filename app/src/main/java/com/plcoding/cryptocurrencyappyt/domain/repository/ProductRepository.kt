package com.plcoding.cryptocurrencyappyt.domain.repository

import com.plcoding.cryptocurrencyappyt.data.remote.dto.ProductDTo
import com.plcoding.cryptocurrencyappyt.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProducts(limit: Int): List<ProductDTo>

    suspend fun getProductList(): List<Product>

    suspend fun getWishList(isFav: Boolean): List<Product>

    suspend fun updateWishProduct(id: Int, isFav: Boolean)

    suspend fun updateWishProductItem(product: Product): Int

    suspend fun getProductCount(): Int

    suspend fun addWishItem(product: List<Product>)
}