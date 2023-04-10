package com.plcoding.cryptocurrencyappyt.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    val category: String?,
    val description: String?,
    @PrimaryKey val id: Int?,
    val image: String?,
    val price: Double?,
    val title: String?,
    var isFavorite: Boolean? = false
)
