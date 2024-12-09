package com.example.restaurantproyectdam.data.controller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantproyectdam.data.model.LoginRequest
import com.example.restaurantproyectdam.data.model.ShowCartModelResponse
import com.example.restaurantproyectdam.data.model.User
import com.example.restaurantproyectdam.data.model.UserUpdateRequest
import com.example.restaurantproyectdam.data.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class UpdateViewModel : ViewModel() {

    private val _updateState = MutableStateFlow<UpdateState>(UpdateState.Idle)
    val updateState: StateFlow<UpdateState> = _updateState.asStateFlow()

    fun update(id:Int, name: String?, password: String?, phone: String?) {
        _updateState.value = UpdateState.Loading
        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.updateUser(id,
                    UserUpdateRequest(name, password,phone)
                )
                // Verificar la respuesta del servidor
                if (response.isSuccessful) {
                    val updateResponse = response.body()
                    if (updateResponse?.user != null) {
                        _updateState.value = UpdateState.Success(updateResponse.user)

                    } else {
                        // Respuesta del servidor pero sin usuario válido
                        _updateState.value = UpdateState.Error("Usuario o contraseña incorrectos")
                    }
                } else {
                    // Error en la respuesta (e.g., 4xx o 5xx)
                    _updateState.value =
                        UpdateState.Error(
                            "Error: ${
                                response.errorBody()?.string() ?: "Error desconocido"
                            }"
                        )
                }
            } catch (e: Exception) {
                _updateState.value = UpdateState.Error("Error de red: ${e.message}")
            }
        }
    }
}

sealed class UpdateState {
    object Idle : UpdateState()
    object Loading : UpdateState()
    data class Success(val user: User) : UpdateState()
    data class Error(val message: String) : UpdateState()
}