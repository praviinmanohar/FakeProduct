package com.plcoding.cryptocurrencyappyt.presentation.product_list

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.model.Product
import com.plcoding.cryptocurrencyappyt.domain.use_case.get_products.GetProductsUseCase
import com.plcoding.cryptocurrencyappyt.domain.use_case.wish_list.FavoriteProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val favoriteProductUseCase: FavoriteProductUseCase
): ViewModel() {

    private val _state = mutableStateOf(ProductListState())
    val state: State<ProductListState> = _state

    private val _isFavState = mutableStateOf(false)
    val isFavState: State<Boolean> = _isFavState

    init {
         favoriteProductUseCase.getProductCountUseCase().onEach {
             if (it) {
                 getCachedProducts()
             } else {
                 getProducts()
             }
         }.launchIn(viewModelScope)
    }

    private fun getCachedProducts() {
        favoriteProductUseCase.getProductListUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = ProductListState(products = result.data?: emptyList())
                    saveProduct(result.data?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ProductListState(error = result.message?: "Unexpected error occur")
                }
                is Resource.Loading -> {
                    _state.value = ProductListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getProducts() {
        getProductsUseCase(10).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = ProductListState(products = result.data?: emptyList())
                    saveProduct(result.data?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ProductListState(error = result.message?: "Unexpected error occur")
                }
                is Resource.Loading -> {
                    _state.value = ProductListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getWishedProduct() {
        favoriteProductUseCase.getWishListUseCase(isFav = true).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = ProductListState(products = result.data?: emptyList())
                    saveProduct(result.data?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ProductListState(error = result.message?: "Unexpected error occur")
                }
                is Resource.Loading -> {
                    _state.value = ProductListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun saveProduct(product: List<Product>) {
        viewModelScope.launch { favoriteProductUseCase.addWishItemUseCase(product = product) }
    }

    fun onEvent(isAdd: Boolean) {
        if (isAdd) {
            getCachedProducts()
            _isFavState.value = false
        } else {
            getWishedProduct()
            _isFavState.value = true
        }
    }

    fun updateFavoriteProduct(product: Product, ctx:Context) {
        favoriteProductUseCase.updateWishedProductUseCase(product).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    Log.e("TAG", "updateFavoriteProduct: ===>>> $result" )
                    Toast.makeText(ctx, "Updated successfully", Toast.LENGTH_SHORT).show()
                    getCachedProducts()
                }
                is Resource.Error -> {
                    Log.e("TAG", "updateFavoriteProduct: ===>>> ${result.message}" )
                    Toast.makeText(ctx, "${result.message}", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    _state.value = ProductListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}