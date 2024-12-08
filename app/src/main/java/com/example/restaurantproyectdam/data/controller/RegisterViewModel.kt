package com.example.restaurantproyectdam.data.controller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.restaurantproyectdam.data.model.CartModelRequest
import com.example.restaurantproyectdam.data.model.RegisterRequest
import com.example.restaurantproyectdam.data.model.RegisterUser
import com.example.restaurantproyectdam.data.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> = _registerState.asStateFlow()

    val api = RetrofitClient.api

    fun register(email: String, password: String, name: String) {
        _registerState.value = RegisterState.Loading
        viewModelScope.launch {
            try {
                val response = api.registerUser(RegisterRequest(name, email, password))
                // Verificar la respuesta del servidor
                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    if (registerResponse?.user != null) {
                        // Registro exitoso, actualizar el estado
                        _registerState.value = RegisterState.Success(registerResponse.user)
                        // creamos el carrito con el registro del usuario
                        api.createCart(CartModelRequest(registerResponse.user.user_id))
                    } else {
                        // Respuesta del servidor pero sin usuario válido
                        _registerState.value = RegisterState.Error("Error: validacion")
                    }
                } else {
                    // Error en la respuesta (e.g., 4xx o 5xx)
                    _registerState.value = RegisterState.Error(
                        "Error: ${response.errorBody()?.string() ?: "Error desconocido"}"
                    )
                }
            } catch (e: Exception) {
                _registerState.value = RegisterState.Error("Error de red: ${e.message}")
            }
        }
    }

}


sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    data class Success(val registerUser: RegisterUser) : RegisterState()
    data class Error(val message: String) : RegisterState()
}