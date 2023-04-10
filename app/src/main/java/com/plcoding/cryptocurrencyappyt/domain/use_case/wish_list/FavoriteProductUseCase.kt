package com.plcoding.cryptocurrencyappyt.domain.use_case.wish_list

data class FavoriteProductUseCase(
    val getProductListUseCase: GetProductListUseCase,
    val getWishListUseCase: GetWishListUseCase,
    val addWishItemUseCase: AddWishItemUseCase,
    val getProductCountUseCase: GetProductCountUseCase,
    val updateWishedProductUseCase: UpdateWishedProductUseCase
)
