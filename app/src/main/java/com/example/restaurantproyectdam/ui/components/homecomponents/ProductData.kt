package com.example.restaurantproyectdam.ui.components.homecomponents

import androidx.annotation.DrawableRes
import com.example.restaurantproyectdam.R

data class ProductData(
    val title: String,
    val description: String,
    val cost: Float,
    @DrawableRes val image: Int
)
val pages = listOf(
    ProductData(
        title = "Tempura",
        description ="Tempura is a Japanese dish of lightly battered and deep-fried vegetables and seafood that's known for its unique crispy, non-greasy texture. ",
        cost = 200.00f,
        image = R.drawable.tempura
    ),
    ProductData(
        title = "Dango",
        description = "Dango is a Japanese dumpling made from rice flour and glutinous rice flour, and is a popular confectionery in the country",
        cost = 100f,
        image = R.drawable.dango
    ),
    ProductData(
        title = "Onigiri",
        description = "Onigiri is a Japanese snack made of steamed rice that is formed into a ball, triangle, or cylinder shape and often wrapped in nori (seaweed). ",
        cost = 250f,
        image = R.drawable.onigiri
    ),
    ProductData(
        title = "Sushi",
        description = "Sushi is a Japanese dish of vinegared rice with a variety of toppings, such as vegetables, seafood, or egg",
        cost = 200f,
        image = R.drawable.sushi
    )


)