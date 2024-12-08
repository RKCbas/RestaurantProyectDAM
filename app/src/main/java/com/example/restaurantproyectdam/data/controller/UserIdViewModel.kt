package com.example.restaurantproyectdam.data.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UserIdViewModel : ViewModel() {
    var userId by mutableStateOf<Int?>(null)
        private set

    fun UpdateUserId(id: Int){
        userId = id
    }

    fun clearUserId() {
        userId = null
    }
}