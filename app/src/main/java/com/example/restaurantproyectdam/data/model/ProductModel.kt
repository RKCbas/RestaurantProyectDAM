package com.example.restaurantproyectdam.data.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.restaurantproyectdam.R

data class ProductModel(
    val id: Int,
    val title: String,
    val description: String,
    val cost: Float,
    val image: Painter,
    val category: Int
)


@Composable
fun createArrayProducts(): Array<ProductModel> {
    val arrayProducts = arrayOf(
        ProductModel(
            1,
            "Tempura",
            "Tempura is a Japanese dish of lightly battered and deep-fried vegetables and seafood that's known for its unique crispy, non-greasy texture. ",
            200.00f,
            painterResource(R.drawable.tempura),
            1
        ),

        ProductModel(
            2,
            "Dango",
            "Dango is a Japanese dessert made from rice flour and glutinous rice flour, and is a popular confectionery in the country",
            100.00f,
            painterResource(R.drawable.dango),
            2
        ),
        ProductModel(
            3,
            "Onigiri",
            "Onigiri is a Japanese snack made of steamed rice that is formed into a ball, triangle, or cylinder shape and often wrapped in nori (seaweed). ",
            250.00f,
            painterResource(R.drawable.onigiri),
            3
        ),
        ProductModel(
            4,
            "Sushi",
            "Sushi is a Japanese dish of vinegared rice with a variety of toppings, such as vegetables, seafood, or egg",
            200.00f,
            painterResource(R.drawable.sushi),
            3
        ),
    )
    return arrayProducts
}

