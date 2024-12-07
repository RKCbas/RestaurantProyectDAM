package com.example.restaurantproyectdam.data.model

import com.google.android.gms.common.internal.Objects

data class CartModelWithContent(
    val cart_id: Int = 0,
    val user_id: Int = 0,
    val created_at: String = "",
    val updated_at: String="",
    val dishes: List<DishWithPivot>
)

data class CartModelRequest(
    val user_id :Int = 0
)
data class CartModelResponse(
    val user_id: Int= 0,
    val updated_at: String="",
    val created_at: String= "",
    val cart_id: Int =0
)
data class ShowCartModelResponse(
    val cart_id: Int =0,
    val user_id: Int= 0,
    val created_at: String= "",
    val updated_at: String=""

)
data class DeleteCartModelResponse(
    val message : String =""
)



data class AddToCartModelRequest(
    val quantity: Int = 0
)
data class CartModelActionResponse (
    val message: String = "",
    val cart: DishWithPivot
)
data class CartOnlyContent(
    val dishes: List<DishWithPivot>
)