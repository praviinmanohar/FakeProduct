package com.plcoding.cryptocurrencyappyt.presentation.product_list

import com.plcoding.cryptocurrencyappyt.domain.model.Product

data class ProductListState (
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String = ""
)