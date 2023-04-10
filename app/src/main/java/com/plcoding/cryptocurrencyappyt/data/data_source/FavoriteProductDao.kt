package com.plcoding.cryptocurrencyappyt.data.data_source

import androidx.room.*
import com.plcoding.cryptocurrencyappyt.domain.model.Product

@Dao
interface FavoriteProductDao {

    @Query("SELECT * FROM product")
    suspend fun getProductList(): List<Product>

    @Query("SELECT * FROM product WHERE isFavorite = :isFav")
    suspend fun getWishList(isFav: Boolean): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTOWishList(product: List<Product>)

    @Query("SELECT COUNT(*) FROM product")
    suspend fun getProductCount(): Int

    @Query("UPDATE product SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateProduct(isFavorite: Boolean, id: Int)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProductItem(product: Product): Int

    @Delete
    suspend fun deleteWishedItem(product: Product)
}