package com.plcoding.cryptocurrencyappyt.data.repository

import android.util.Log
import com.plcoding.cryptocurrencyappyt.data.data_source.FavoriteProductDao
import com.plcoding.cryptocurrencyappyt.data.remote.dto.ProductDTo
import com.plcoding.cryptocurrencyappyt.data.remote.FakeStoreApi
import com.plcoding.cryptocurrencyappyt.domain.model.Product
import com.plcoding.cryptocurrencyappyt.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: FakeStoreApi,
    private val dao: FavoriteProductDao
) : ProductRepository {
    override suspend fun getProducts(limit: Int): List<ProductDTo> = api.getFakeProducts(limit)

    override suspend fun getProductList(): List<Product> = dao.getProductList()

    override suspend fun getWishList(isFav: Boolean): List<Product> = dao.getWishList(isFav)

    override suspend fun updateWishProduct(id: Int, isFav: Boolean) = dao.updateProduct(id = id, isFavorite = isFav)

    override suspend fun updateWishProductItem(product: Product) : Int {
        val status = dao.updateProductItem(product)
        Log.e("TAG", "updateWishProductItem: ===>>> $status", )
        return status
    }

    override suspend fun getProductCount(): Int = dao.getProductCount()

    override suspend fun addWishItem(product: List<Product>) = dao.addTOWishList(product)
}