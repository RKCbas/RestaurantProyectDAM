package com.example.restaurantproyectdam.data.controller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantproyectdam.data.model.LoginRequest
import com.example.restaurantproyectdam.data.model.ShowCartModelResponse
import com.example.restaurantproyectdam.data.model.User
import com.example.restaurantproyectdam.data.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState.asStateFlow()

    fun login(email: String, password: String) {
        _loginState.value = LoginState.Loading
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.loginUser(LoginRequest(email, password))
                // Verificar la respuesta del servidor
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse?.user != null) {
                        val cartResponse = RetrofitClient.api.showCart(loginResponse.user.user_id)
                        if(cartResponse.isSuccessful){
                            val cartResponseBody = cartResponse.body()
                            println(cartResponseBody)

                            if(cartResponseBody != null){
                                // Login exitoso, actualizar el estado
                                _loginState.value = LoginState.Success(loginResponse.user, cartResponseBody)
                            }
                        }

                    } else {
                        // Respuesta del servidor pero sin usuario válido
                        _loginState.value = LoginState.Error("Usuario o contraseña incorrectos")
                    }
                } else {
                    // Error en la respuesta (e.g., 4xx o 5xx)
                    _loginState.value =
                        LoginState.Error(
                            "Error: ${
                                response.errorBody()?.string() ?: "Error desconocido"
                            }"
                        )
                }
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("Error de red: ${e.message}")
            }
        }
    }

}


sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val user: User, val cart: ShowCartModelResponse) : LoginState()
    data class Error(val message: String) : LoginState()
}