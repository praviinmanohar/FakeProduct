package com.plcoding.cryptocurrencyappyt.domain.use_case.wish_list

import android.util.Log
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.model.Product
import com.plcoding.cryptocurrencyappyt.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWishListUseCase (
    private val repository: ProductRepository
) {
    operator fun invoke(isFav: Boolean): Flow<Resource<List<Product>>> = flow {
        try {
            emit(Resource.Loading<List<Product>>())
            val product = repository.getWishList(isFav)
            Log.e("TAG", "invoke: ===>>>> $product" )
            emit(Resource.Success<List<Product>>(product))
        } catch (e: Exception) {
            emit(Resource.Error<List<Product>>(e.localizedMessage?: "An unexpected error occur"))
        }
    }
}