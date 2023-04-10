package com.plcoding.cryptocurrencyappyt.presentation

sealed class Screen (val route: String) {
    object ProductListScreen: Screen (route = "product_list_screen")
    object ProductWishListScreen: Screen (route = "product_wishlist_screen")
}
