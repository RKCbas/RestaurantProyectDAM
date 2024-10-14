package com.example.restaurantproyectdam.ui.screens

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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
import androidx.navigation.compose.rememberNavController
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.data.model.OrderCardModel
import com.example.restaurantproyectdam.data.model.OrderProduct
import com.example.restaurantproyectdam.data.model.ProductModel
import com.example.restaurantproyectdam.ui.theme.OnPrimary
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider

@Composable
fun OrderScreen(navController: NavController, id: Int) {
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
            )
        )

    )
    val orderCardsArray = arrayOf(
        OrderCardModel(id = 1, status = true, orderProducts = orderProductsArray),
        OrderCardModel(id = 2, status = false, orderProducts = orderProductsArray),
        OrderCardModel(id = 3, status = true, orderProducts = orderProductsArray)
    )
    val specificOrder = orderCardsArray.find { it.id == id }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFDDDEDD))
                    .padding(10.dp, 10.dp, 15.dp, 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back arrow",
                        tint = Color.Black
                    )
                }
                Column {
                    Text(
                        text = "Order",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "id # $id",
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
                Box(
                    modifier = Modifier.width(24.dp)
                ) {
                    Text(text = "")
                }
            }

            LazyColumn(
                modifier = Modifier.padding(10.dp)
            ) {
                if (specificOrder != null) {
                    items(specificOrder.orderProducts) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = item.orderProduct.title,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .padding(5.dp)
                                )
                            }
                            HorizontalDivider(thickness = 5.dp)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 5.dp)
                            ) {
                                Column {
                                    Image(
                                        modifier = Modifier
                                            .width(150.dp)
                                            .height(175.dp)
                                            .padding(start = 10.dp),
                                        painter = item.orderProduct.image,
                                        contentDescription = "product Image",
                                    )

                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(5.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Description",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                    Text(
                                        text = item.orderProduct.description,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Normal,
                                        modifier = Modifier.padding(bottom = 5.dp)
                                    )
                                    Text(
                                        text = "Quantity",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                    Text(
                                        text = item.amount.toString(),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Normal,
                                        modifier = Modifier.padding(bottom = 5.dp)
                                    )
                                    Text(
                                        text = "Cost",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                    val totalCost = item.amount * item.orderProduct.cost
                                    Text(
                                        text = totalCost.toString(),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Normal,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun OrderScreenPreview() {

    OrderScreen(rememberNavController(), id = 1)

}