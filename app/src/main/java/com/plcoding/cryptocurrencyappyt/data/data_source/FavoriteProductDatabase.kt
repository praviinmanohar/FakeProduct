package com.plcoding.cryptocurrencyappyt.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.plcoding.cryptocurrencyappyt.domain.model.Product

@Database(
    entities = [Product::class],
    version = 1
)
abstract class FavoriteProductDatabase : RoomDatabase() {
    abstract val favoriteProductDao: FavoriteProductDao
}