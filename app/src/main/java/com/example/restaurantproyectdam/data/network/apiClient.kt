package com.example.restaurantproyectdam.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // Aquí va el endpoint de lo que vamos a consumir
    // En caso de que se este corriendo en otra computadora dentro de la misma red
    // private const val BASE_URL = "http://192.168.1.71:8000/api/"
    // En caso de que el servidor este corriendo en la misma computadora
    private const val BASE_URL = "http://192.168.100.220:8000/api/"


    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //JSON Converter
            .build()
            .create(ApiService::class.java)
    }
}