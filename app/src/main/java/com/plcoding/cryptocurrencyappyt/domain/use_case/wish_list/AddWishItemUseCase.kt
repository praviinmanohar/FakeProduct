package com.plcoding.cryptocurrencyappyt.domain.use_case.wish_list

import com.plcoding.cryptocurrencyappyt.domain.model.Product
import com.plcoding.cryptocurrencyappyt.domain.repository.ProductRepository

class AddWishItemUseCase (
    private val repository: ProductRepository
) {
    suspend operator fun invoke(product: List<Product>) {
        repository.addWishItem(product)
    }
}