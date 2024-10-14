package com.example.restaurantproyectdam.ui.components.ordercomponents

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.restaurantproyectdam.data.model.OrderProduct
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.data.model.ProductModel
import androidx.compose.material3.Card
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun OrderCard(id: Int, status: Boolean, products: Array<OrderProduct>, navController: NavController) {

    val statusText = if (status) "Ready" else "Pending"
    val statusColor = if (status) Color.Green else Color.Red

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header (ID and Status)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Order", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = "ID # $id", fontSize = 16.sp)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.End,
                //horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Status: ", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(
                    text = statusText,
                    color = statusColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

//                Spacer(modifier = Modifier.height(8.dp))

            // Product summary
            Text(text = "Summary", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))

            var total by remember { mutableStateOf(0.00) }

            LazyColumn {
                items(products) { product ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        Text(
                            text = "${product.amount} x ${product.orderProduct.title}",
                            fontSize = 16.sp
                        )
                        Text(
                            text = " $${product.amount * product.orderProduct.cost}MXN",
                            fontSize = 16.sp
                        ) // buscar funcion para redondear a 2 decimales
                    }
                    total += product.amount * product.orderProduct.cost
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Total", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = "$${total}MXN", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }


            Spacer(modifier = Modifier.height(8.dp))

            // Details button
            Button(
                onClick = { navController.navigate("OrderScreen/{$id}") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9FA89F))
            ) {
                Text(text = "DETAILS", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OrderCardPreview() {

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

    OrderCard(id = 1, status = false, products = orderProductsArray, navController = rememberNavController())
}