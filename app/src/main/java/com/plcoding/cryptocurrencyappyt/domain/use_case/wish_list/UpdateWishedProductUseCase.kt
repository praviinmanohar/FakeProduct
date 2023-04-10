package com.plcoding.cryptocurrencyappyt.domain.use_case.wish_list

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.model.Product
import com.plcoding.cryptocurrencyappyt.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateWishedProductUseCase (
    private val repository: ProductRepository
) {
    operator fun invoke(product: Product): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
            val status = repository.updateWishProduct(id = product.id?:0, true)
            println("++++===>>>$status<<<<=====+++++")
            emit(Resource.Success<Boolean>(true))
        } catch (e: Exception) {
            emit(Resource.Error<Boolean>(e.localizedMessage?: "An unexpected error occur"))
        }
    }
}
    //updateProduct
