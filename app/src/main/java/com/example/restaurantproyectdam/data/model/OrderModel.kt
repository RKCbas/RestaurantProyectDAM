package com.example.restaurantproyectdam.data.model

data class OrderRequest(
    val user_id : Int = 0,
    val address_id : Int? = 0,
    val table_id : Int ? = 0,
    val status :String = ""
)