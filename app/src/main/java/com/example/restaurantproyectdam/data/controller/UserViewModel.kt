package com.example.restaurantproyectdam.data.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    var userId by mutableStateOf<Int?>(null)
        private set
    var cartId by mutableStateOf<Int?>(null)
        private set

    fun UpdateUserId(id: Int){
        userId = id
    }

    fun UpdateCartId(id: Int){
        cartId = id
    }

    fun clearUserId() {
        userId = null
    }
    fun clearCartId(){
        cartId = null
    }
}