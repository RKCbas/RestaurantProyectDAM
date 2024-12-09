package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.data.model.OrderCardModel
import com.example.restaurantproyectdam.data.model.OrderProduct
import com.example.restaurantproyectdam.data.model.ProductModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.compose.rememberNavController

@Composable
fun OrderScreen(navController: NavController, orderId:Int) {
    val id = 1
    val orderProductsArray = arrayOf(
        OrderProduct(
            amount = 1,
            orderProduct = ProductModel(
                1, "Tempura", "Tempura is a Japanese dish...", 200.00f,
                painterResource(R.drawable.tempura), 1
            )
        ),
        OrderProduct(
            amount = 2,
            orderProduct = ProductModel(
                2, "Dango", "Dango is a Japanese dessert...", 100.00f,
                painterResource(R.drawable.dango), 2
            )
        )
    )
    val orderCardsArray = arrayOf(
        OrderCardModel(id = 1, status = true, orderProducts = orderProductsArray),
        OrderCardModel(id = 2, status = false, orderProducts = orderProductsArray),
        OrderCardModel(id = 3, status = true, orderProducts = orderProductsArray)
    )
    val specificOrder = orderCardsArray.find { it.id == id }
    val orientation = LocalConfiguration.current.orientation

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column{
            // Encabezado con icono de navegación y detalles de la orden
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    //.background(MaterialTheme.colorScheme.surface)
                    .padding(10.dp, 25.dp, 15.dp, 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back arrow",
                        tint = MaterialTheme.colorScheme.surface
                    )
                }
                Column {
                    Text(
                        text = "Order",
                        //color = Color.Black,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "id # $id",
                        style = MaterialTheme.typography.titleSmall,

                        //color = Color.Black,
                        fontSize = 14.sp
                    )
                }
                Box(modifier = Modifier.width(24.dp))
            }

            if (orientation == 1) { // Portrait
                LazyColumn(
                    modifier = Modifier.padding(10.dp)

                ) {
                    specificOrder?.orderProducts?.let { products ->
                        items(products) { item ->
                            OrderProductCard(item)
                        }
                    }
                }
            } else { // Landscape
                LazyRow(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    specificOrder?.orderProducts?.let { products ->
                        items(products) { item ->
                            OrderProductCard(item, Modifier.width(300.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OrderProductCard(item: OrderProduct, modifier: Modifier = Modifier.fillMaxWidth()) {
    Card(
        modifier = modifier.padding(5.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            // Columna de la imagen del producto
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(end = 10.dp),
                    painter = item.orderProduct.image,
                    contentDescription = "product Image",
                )
            }

            // Columna de los detalles del producto
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item.orderProduct.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = item.orderProduct.description,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                )
                Text(
                    text = "Quantity: ${item.amount}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                )
                Text(
                    text = "Total Cost: ${item.amount * item.orderProduct.cost}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun OrderScreenPreview() {
    OrderScreen(navController = rememberNavController(),1)
}
