package com.plcoding.cryptocurrencyappyt.domain.use_case.get_products

import com.plcoding.cryptocurrencyappyt.data.remote.dto.toProduct
import com.plcoding.cryptocurrencyappyt.domain.model.Product
import com.plcoding.cryptocurrencyappyt.domain.repository.ProductRepository
import com.plcoding.cryptocurrencyappyt.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetProductsUseCase @Inject constructor (
    private val repository: ProductRepository
) {
    operator fun invoke(limit: Int): Flow<Resource<List<Product>>> = flow {
        try {
            emit(Resource.Loading<List<Product>>())
            val product = repository.getProducts(limit).map { it.toProduct() }
            emit(Resource.Success<List<Product>>(product))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Product>>(e.localizedMessage?: "An unexpected error occur"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Product>>( "Check your Internet"))
        }
    }
}