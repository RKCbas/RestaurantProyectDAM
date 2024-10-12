package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.data.model.OrderCardModel
import com.example.restaurantproyectdam.data.model.OrderProduct
import com.example.restaurantproyectdam.data.model.ProductModel

@Composable
fun OrderScreen(navController:NavController, id:Int){
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ){
        Row ()
        {

        }
    }
}