package com.example.restaurantproyectdam.ui.components.productsComponents

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.restaurantproyectdam.data.model.ProductModel

@Composable
fun AdminProductsGrid(products:Array<ProductModel>){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        
    }
}