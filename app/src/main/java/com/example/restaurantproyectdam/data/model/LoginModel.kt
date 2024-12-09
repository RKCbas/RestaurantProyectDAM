package com.example.restaurantproyectdam.data.model

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val message: String,
    val user: User? // User puede ser null si el login falla
)

data class User(
    val user_id: Int,
    val name: String,
    val email: String,
    val phone: String?,
    val profile_image: String?,
    val created_at: String,
    val updated_at: String
)

data class UserUpdateRequest(
    val name :String ? ="",
    val password: String ? ="",
    val phone: String ? =""
)
data class UserUpdateResponse(
    val message: String="",
    val user: User
)

