package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.data.model.OrderCardModel
import com.example.restaurantproyectdam.data.model.OrderProduct
import com.example.restaurantproyectdam.data.model.ProductModel
import com.example.restaurantproyectdam.ui.components.AdminBottomBar
import com.example.restaurantproyectdam.ui.components.Header
import com.example.restaurantproyectdam.ui.components.Material3SearchBar
import com.example.restaurantproyectdam.ui.components.ordercomponents.OrderCard
import com.example.restaurantproyectdam.ui.components.ordercomponents.OrderCardAdmin

@Composable
fun AdminOrdersScreen(navController: NavController) {
    Scaffold(
        bottomBar = { AdminBottomBar(navController = navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(R.drawable.logo_fuji),
                    contentDescription = "logo",
                    modifier = Modifier
                        //.size(150.dp)
                        .width(170.dp)
                        .height(100.dp)
                    //tint = Color.Red // Set the color you want here
                )
                Text(
                    "JAPANESE",
                    style = MaterialTheme.typography.titleLarge,
                    //fontSize = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text("Administration of Orders", style = MaterialTheme.typography.titleSmall)
            }

            val orderProductsArray = arrayOf(
                OrderProduct(
                    amount = 1,
                    orderProduct =
                    ProductModel(
                        1,
                        "Tempura",
                        "Tempura is a Japanese dish of lightly battered and deep-fried vegetables and seafood that's known for its unique crispy, non-greasy texture. ",
                        200.00f,
                        painterResource(R.drawable.tempura),
                        1
                    )
                ),
                OrderProduct(
                    amount = 2,
                    orderProduct =
                    ProductModel(
                        2,
                        "Dango",
                        "Dango is a Japanese dessert made from rice flour and glutinous rice flour, and is a popular confectionery in the country",
                        100.00f,
                        painterResource(R.drawable.dango),
                        2
                    ),
                    extraDetails = "without many"
                )

            )
            val orderCardsArray = arrayOf(
                OrderCardModel(id = 1, status = true, orderProducts = orderProductsArray),
                OrderCardModel(id = 2, status = false, orderProducts = orderProductsArray),
                OrderCardModel(id = 3, status = true, orderProducts = orderProductsArray)
            )

            LazyColumn {
                items(orderCardsArray){ orderCard ->
                    OrderCardAdmin(orderCard = orderCard)
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun AdminOrdersScreenPreview() {
    AdminOrdersScreen(navController = rememberNavController())
}

