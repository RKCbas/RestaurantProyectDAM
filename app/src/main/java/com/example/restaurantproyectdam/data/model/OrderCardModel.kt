package com.example.restaurantproyectdam.data.model

data class OrderCardModel(
    val id:Int,
    val status:Boolean,
    val orderProducts: Array<OrderProduct>
)
