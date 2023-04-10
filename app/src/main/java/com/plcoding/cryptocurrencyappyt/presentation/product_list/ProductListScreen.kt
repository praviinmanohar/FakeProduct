package com.plcoding.cryptocurrencyappyt.presentation.product_list

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.cryptocurrencyappyt.presentation.product_list.components.ProductListItem

@Composable
fun ProductListScreen(
    navController: NavController,
    ctx: Context,
    viewModel: ProductListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    var color = remember {
      mutableStateOf(Color.Red)
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Fake products",
                style = MaterialTheme.typography.h5
            )
            IconButton(
                onClick = {
                    if (!viewModel.isFavState.value) {
                        color.value = Color.Blue
                        viewModel.onEvent(viewModel.isFavState.value)
                    } else {
                        color.value = Color.Red
                        viewModel.onEvent(viewModel.isFavState.value)
                    }
                },
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = color.value
                )
            }
        }

        Box( modifier = Modifier.fillMaxSize() ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items (state.products){ product ->
                    ProductListItem (
                        product = product,
                        onItemClick = {
                            println("${product.title}")
                            //viewModel.onEvent(true, product)
                            viewModel.updateFavoriteProduct(product, ctx)
                        }
                    )
                }
            }
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }


}
