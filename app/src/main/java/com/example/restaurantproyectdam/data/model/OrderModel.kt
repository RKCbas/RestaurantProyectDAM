package com.example.restaurantproyectdam.data.model

data class CreateOrderRequest(
    val user_id : Int = 0,
    val table_id : Int ? = 0,
    val status :String = ""
)
data class CreateOrderResponse(
    val user_id : Int = 0,
    val table_id : Int ? = 0,
    val status :String = "",
    val updated_at : String ="",
    val created_at: String ="",
    val order_id : Int = 0
)

data class Order(
    val order_id : Int = 0,
    val user_id : Int = 0,
    val table_id: Int? = 0,
    val status : String = "",
    val updated_at : String ="",
    val created_at: String ="",
    val user : User
)
data class OrderAndDish(

    val order_id: Int = 0,
    val user_id: Int =  0,
    val address_id: Int? = 0,
    val table_id: Int =0,
    val status:String="",
    val created_at: String= "",
    val updated_at: String ="",
    val dishes: List<DishWithPivot>
)
data class OrderContent(
    val dishes: List<DishWithPivot>
)
data class DeleteDishFromOrderResponse(
    val message : String ="",
    val order: OrderAndDish
)
data class AddToOrderModelRequest(
    val quantity: Int = 0
)

data class AddToOrderModelResponse(
    val message: String,
    val order: OrderAndDish
)