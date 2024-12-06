package com.example.projecto1.data.network

import com.example.restaurantproyectdam.data.model.LoginRequest
import com.example.restaurantproyectdam.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    //User
        //login
    @POST("users/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

}