package com.example.restaurantproyectdam.data.controller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantproyectdam.data.model.AddToOrderModelRequest
import com.example.restaurantproyectdam.data.model.AddToOrderModelResponse
import com.example.restaurantproyectdam.data.model.CreateOrderRequest
import com.example.restaurantproyectdam.data.model.CreateOrderResponse
import com.example.restaurantproyectdam.data.model.DeleteDishFromOrderResponse
import com.example.restaurantproyectdam.data.model.Order
import com.example.restaurantproyectdam.data.model.OrderContent

import com.example.restaurantproyectdam.data.network.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Response

import kotlinx.coroutines.launch


class OrderViewModel: ViewModel() {
    val api = RetrofitClient.api

    fun createOrder(order: CreateOrderRequest,
                    onResult: (Response<CreateOrderResponse>) -> Unit
    ){
        viewModelScope.launch {
            try{
                val response = api.createOrder(order)
                onResult(response)
            }catch(exeption: Exception){
                print(exeption)
            }
        }
    }

    fun getOrdersFromUser(user_id: Int,
                 onResult: (Response<List<Order>>) -> Unit
    ){
        viewModelScope.launch {
            try {
                val response = api.getOrdersFromUser(user_id)
                onResult(response)
            }catch (exception: Exception){
                print(exception)
            }
        }
    }
    fun getDishesFromOrder(
        order_id: Int,
        onResult: (Response<OrderContent>) -> Unit
    ){
        viewModelScope.launch {
            try {
                val response = api.getDishesFromOrder(order_id)
                onResult(response)
            }catch (exception: Exception){
                print(exception)
            }
        }
    }
    fun deleteDishFromOrder(
        order_id: Int,
        dish_id: Int,
        onResult: (Response<DeleteDishFromOrderResponse>) -> Unit
    ){
        viewModelScope.launch {
            try {
                val response = api.deleteDishFromOrder(order_id,dish_id)
                onResult(response)
            }catch (exception: Exception){
                print(exception)
            }
        }
    }
    fun addDishToOrder(
        order_id: Int,
        dish_id: Int,
        addToOrderModelRequest: AddToOrderModelRequest,
        onResult: (Response<AddToOrderModelResponse>) -> Unit
    ){
        viewModelScope.launch {
            try {
                val response = api.addDishToOrder(order_id,dish_id,addToOrderModelRequest)
                onResult(response)
            }catch (exception: Exception){
                print(exception)
            }
        }
    }

}