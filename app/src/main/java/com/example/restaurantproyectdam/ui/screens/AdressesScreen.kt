package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Mis Direcciones") })
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Aquí puedes agregar el contenido de tu pantalla, por ejemplo, una lista de direcciones.
                Text("Aquí van las direcciones del usuario.")
            }
        }
    )
}