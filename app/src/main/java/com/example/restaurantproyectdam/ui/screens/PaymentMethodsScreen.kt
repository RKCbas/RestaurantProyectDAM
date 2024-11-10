package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentMethodsScreen(navController: NavController) {
    val paymentMethods = remember { mutableStateListOf("Visa", "Mastercard", "PayPal") } // Lista de métodos de pago
    var showAddPaymentMethodDialog by remember { mutableStateOf(false) }
    var selectedMethod by remember { mutableStateOf<String?>(null) } // Método de pago seleccionado

    // Estados para los campos del formulario
    var newName by remember { mutableStateOf("") }
    var newPhone by remember { mutableStateOf("") }
    var newEmail by remember { mutableStateOf("") }
    var newCardNumber by remember { mutableStateOf("") }
    var newCardExpiry by remember { mutableStateOf("") }
    var isCreditCard by remember { mutableStateOf(true) }

    // Estados para los campos resaltados en rojo si están vacíos
    var nameError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var cardNumberError by remember { mutableStateOf(false) }
    var cardExpiryError by remember { mutableStateOf(false) }

    // Función para validar si los campos están completos
    fun isFormValid(): Boolean {
        nameError = newName.isBlank()
        phoneError = newPhone.isBlank()
        emailError = newEmail.isBlank()
        cardNumberError = newCardNumber.isBlank()
        cardExpiryError = newCardExpiry.isBlank()
        return !nameError && !phoneError && !emailError && !cardNumberError && !cardExpiryError
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddPaymentMethodDialog = true }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar Método")
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Icon of arrow back",
                    modifier = Modifier
                        .clickable { navController.navigate("profile") }
                        .padding(top = 10.dp, start = 23.dp),
                    tint = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(20.dp))

                // Titulo de la sección
                Text(
                    text = "Your Payment Methods c:",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )

                Spacer(modifier = Modifier.height(35.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(paymentMethods) { method ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable {
                                    selectedMethod = method // Establecer el método seleccionado
                                }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(method)
                                IconButton(
                                    onClick = {
                                        // Eliminar el método de pago de la lista
                                        paymentMethods.remove(method)
                                    }
                                ) {
                                    Icon(Icons.Filled.Delete, contentDescription = "Eliminar")
                                }
                            }
                        }
                    }
                }
            }
        }
    )

    // Diálogo para agregar nuevo método de pago
    if (showAddPaymentMethodDialog) {
        AlertDialog(
            onDismissRequest = { showAddPaymentMethodDialog = false },
            title = { Text("Agregar Nuevo Método de Pago") },
            text = {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    item {
                        // Nombre completo
                        TextField(
                            value = newName,
                            onValueChange = { newName = it },
                            label = { Text("Nombre Completo") },
                            isError = nameError,
                            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                        )
                    }

                    item {
                        // Teléfono
                        TextField(
                            value = newPhone,
                            onValueChange = { newPhone = it },
                            label = { Text("Teléfono") },
                            isError = phoneError,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                        )
                    }

                    item {
                        // Correo electrónico
                        TextField(
                            value = newEmail,
                            onValueChange = { newEmail = it },
                            label = { Text("Correo Electrónico") },
                            isError = emailError,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                        )
                    }

                    item {
                        // Número de tarjeta
                        TextField(
                            value = newCardNumber,
                            onValueChange = { newCardNumber = it },
                            label = { Text("Número de Tarjeta") },
                            isError = cardNumberError,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                        )
                    }

                    item {
                        // Expiración de la tarjeta (MM/YY)
                        TextField(
                            value = newCardExpiry,
                            onValueChange = { newCardExpiry = it },
                            label = { Text("MM/YY") },
                            isError = cardExpiryError,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                        )
                    }

                    item {
                        // Checkbox para seleccionar si es Crédito o Débito
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = isCreditCard,
                                onCheckedChange = { isCreditCard = it }
                            )
                            Text(text = if (isCreditCard) "Crédito" else "Débito")
                        }
                    }
                }

            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (isFormValid()) {
                            // Agregar el método de pago a la lista
                            paymentMethods.add("$newName - ${newCardNumber.take(4)}****") // Ejemplo de como agregar con un resumen
                            // Limpiar los campos
                            newName = ""
                            newPhone = ""
                            newEmail = ""
                            newCardNumber = ""
                            newCardExpiry = ""
                            isCreditCard = true
                            showAddPaymentMethodDialog = false
                        }
                    }
                ) {
                    Text("Agregar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddPaymentMethodDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    // Mostrar detalles del método de pago seleccionado
    selectedMethod?.let { method ->
        showPaymentDetailsDialog(method) {
            selectedMethod = null
        }
    }
}

@Composable
fun showPaymentDetailsDialog(method: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() }, // Llamamos al callback cuando se cierra
        title = { Text("Detalles del Método de Pago") },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Método de pago: $method")
            }
        },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cerrar")
            }
        }
    )
}
