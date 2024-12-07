package com.example.restaurantproyectdam.data.model



data class DishModel(
    val dish_id: Int = 0,
    val name :String ="",
    val description: String = "",
    val price: Float =0.0f,
    val ingredients: String = "",
    val dish_image: String ="",
    val prepartion_time: Int =0,
    val created_at: String = "",
    val updated_at: String = ""
)

data class DishWithPivot(
    val dish_id: Int = 0,
    val name :String ="",
    val description: String = "",
    val price: Float =0.0f,
    val ingredients: String = "",
    val dish_image: String ="",
    val prepartion_time: Int =0,
    val created_at: String = "",
    val updated_at: String = "",
    val pivot: Pivot
)
data class Pivot(
    val cart_id: Int = 0,
    val dish_id: Int = 0,
    val quantity: Int = 0,
    val total_price: Float = 0.0f,
    val created_at: String="",
    val updated_at:String =""
)