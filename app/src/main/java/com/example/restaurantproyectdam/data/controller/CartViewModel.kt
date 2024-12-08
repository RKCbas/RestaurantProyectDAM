package com.example.restaurantproyectdam.data.controller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantproyectdam.data.model.AddToCartModelRequest
import com.example.restaurantproyectdam.data.model.CartModelActionResponse
import com.example.restaurantproyectdam.data.model.CartModelRequest
import com.example.restaurantproyectdam.data.model.CartModelResponse
import com.example.restaurantproyectdam.data.model.CartModelWithContent
import com.example.restaurantproyectdam.data.model.CartOnlyContent
import com.example.restaurantproyectdam.data.model.DeleteCartModelResponse
import com.example.restaurantproyectdam.data.model.ShowCartModelResponse
import com.example.restaurantproyectdam.data.network.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Response

class CartViewModel: ViewModel() {
    val api = RetrofitClient.api

    fun createCart(cart: CartModelRequest, onResult: (Response<CartModelResponse>) -> Unit){
        viewModelScope.launch {
            try{
                val response = api.createCart(cart)
                onResult(response)
            }catch(exeption: Exception){
                print(exeption)
            }
        }
    }

    fun showCart(id: Int, onResult: (Response<ShowCartModelResponse>) -> Unit ){
        viewModelScope.launch {
            try {
                val response = api.showCart(id)
                onResult(response)
            }catch (expection: Exception){
                print(expection)
            }
        }
    }

    fun deleteCart(id: Int, onResult: (Response<DeleteCartModelResponse>) -> Unit){
        try {
            viewModelScope.launch {
                val response = api.deleteCart(id)
                onResult(response)
            }
        }catch(exception : Exception){
            print(exception)
        }
    }

    fun addToCart(
        cart_id: Int,
        dish_id:Int,
        addToCartModel: AddToCartModelRequest,
        onResult: (Response<CartModelActionResponse>) -> Unit
    ){
        try {
            viewModelScope.launch {
                val response = api.addToCart(cart_id,dish_id,addToCartModel)
                onResult(response)
            }
        }catch(exception : Exception){
            print(exception)
        }
    }

    fun removeFromCart(
        cart_id: Int,
        dish_id:Int,
        onResult: (Response<CartModelActionResponse>) -> Unit
    ){
        try {
            viewModelScope.launch {
                val response = api.removeDishFromCart(cart_id,dish_id)
                onResult(response)
            }
        }catch(exception : Exception){
            print(exception)
        }
    }

    fun showCartContent(
        cart_id: Int,
        onResult: (Response<CartOnlyContent>) -> Unit
    ){
        try {
            viewModelScope.launch {
                val response = api.showCartContent(cart_id)
                onResult(response)
            }
        }catch(exception : Exception){
            print(exception)
        }
    }
}