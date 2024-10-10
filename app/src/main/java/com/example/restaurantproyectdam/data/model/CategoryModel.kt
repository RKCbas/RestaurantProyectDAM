package com.example.restaurantproyectdam.data.model

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.restaurantproyectdam.R

data class CategoryModel(
    val id:Int,
    val name:String,
    val image:Painter
)