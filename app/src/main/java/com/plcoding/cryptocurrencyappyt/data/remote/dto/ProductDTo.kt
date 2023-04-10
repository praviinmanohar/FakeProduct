package com.plcoding.cryptocurrencyappyt.data.remote.dto

import com.example.fakeproduct.data.remote.dto.Rating
import com.plcoding.cryptocurrencyappyt.domain.model.Product

data class ProductDTo(
    val category: String?,
    val description: String?,
    val id: Int?,
    val image: String?,
    val price: Double?,
    val rating: Rating?,
    val title: String?
)

fun ProductDTo.toProduct(): Product {
    return Product(
        category = category,
        description = description,
        id = id,
        image = image,
        price = price,
        title = title
    )
}