package com.plcoding.cryptocurrencyappyt.presentation.product_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.plcoding.cryptocurrencyappyt.domain.model.Product

@Composable
fun ProductListItem(
    product: Product,
    onItemClick: (Product) -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            //.clickable { onItemClick(product) }
            .padding(10.dp),
        horizontalArrangement = Arrangement.Start
        ) {
        Image(
            painter = rememberAsyncImagePainter(product.image),
            contentDescription = product.description,
            modifier = Modifier
                .size(100.dp)
                .padding(10.dp)
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "${product.title?.trim()}",
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )
            /*Icon(imageVector = Icons.Default.Favorite , contentDescription = "Wishlist")*/
            if (product.isFavorite != true) {
                Spacer(modifier = Modifier.padding(5.dp))
                Button(
                    onClick = {
                        onItemClick(product)
                    },
                ) {
                    Text(text = "Add to Wish list")
                }
            }
        }
    }
}