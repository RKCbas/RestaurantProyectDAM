package com.example.restaurantproyectdam.data.model

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

data class RegisterResponse(
    val message: String,
    val registerUser: RegisterUser
)

data class RegisterUser(
    val name: String,
    val email: String,
    val created_at: String,
    val updated_at: String,
    val user_id: Int
)
