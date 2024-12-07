package com.example.restaurantproyectdam.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.data.model.OrderCardModel
import com.example.restaurantproyectdam.data.model.OrderProduct
import com.example.restaurantproyectdam.data.model.ProductModel
import com.example.restaurantproyectdam.ui.components.BottomBar
import com.example.restaurantproyectdam.ui.components.Header

@Composable
fun CartScreen(navController: NavController,userId:Int) {

    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Header("CART")
            Content(navController)
        }
    }
}


@Composable
private fun Content(navController: NavController){
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

            if (orientation == 1) { // Portrait
                LazyColumn(
                    modifier = Modifier.padding(10.dp)

                ) {
                    specificOrder?.orderProducts?.let { products ->
                        items(products) { item ->
                            ProductCart(item)
                        }

                    }

                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "Order")
                }
            } else { // Landscape
                LazyRow(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    specificOrder?.orderProducts?.let { products ->
                        items(products) { item ->
                            ProductCart(item, Modifier.width(300.dp))
                        }
                    }
                }
                Button(
                    modifier = Modifier.width(300.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "Order")
                }
            }
        }
    }
}
@Composable
private fun ProductCart(item: OrderProduct, modifier: Modifier = Modifier.fillMaxWidth()) {
    Card(
        modifier = modifier.padding(5.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),

    ) {
        CardContent(item = item)
    }
}

@Composable
private fun CardContent(item: OrderProduct){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
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
            Column(
                modifier = Modifier
                    .weight(0.8f)
                    .padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Button(

                    onClick = {
                        //Cancelar
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
                ) {
                    Icon(imageVector =  Icons.Filled.Delete, contentDescription = "")
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CartScreenPreview() {
    CartScreen(navController = rememberNavController(),1)
}