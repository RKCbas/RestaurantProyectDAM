package com.example.restaurantproyectdam.data.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {


    //User ID for the session
    var userId by mutableStateOf<Int?>(null)
        private set

    fun UpdateUserId(id: Int) {
        userId = id
    }

    fun clearUserId() {
        userId = null
    }

    //Cart ID for the User
    var cartId by mutableStateOf<Int?>(null)
        private set

    fun UpdateCartId(id: Int) {
        cartId = id
    }

    //Table ID for the order
    var tableId by mutableStateOf<Int?>(0)
        private set

    fun updateTableId(id: Int?) {
        tableId = id
    }


}