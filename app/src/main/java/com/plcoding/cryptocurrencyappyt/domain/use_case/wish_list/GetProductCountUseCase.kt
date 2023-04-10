package com.plcoding.cryptocurrencyappyt.domain.use_case.wish_list

import com.plcoding.cryptocurrencyappyt.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetProductCountUseCase (
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<Boolean> = flow {
        val count = repository.getProductCount()
        emit(count>0)
    }
}