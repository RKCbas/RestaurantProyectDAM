package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf


import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.example.restaurantproyectdam.ui.components.BottomBar

import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState

@Composable
fun ContactUsScreen(navController: NavController) {
    // Declarar las variables para los inputs
    var subject by rememberSaveable { mutableStateOf("") }
    var branch by rememberSaveable { mutableStateOf("") }
    var message by rememberSaveable { mutableStateOf("") }

    val scrollState = rememberScrollState() // Estado de desplazamiento

    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState) // Aplicar scroll
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
                    text = "We'd love to hear you!",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )

                Spacer(modifier = Modifier.height(35.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    // Input para Asunto
                    TextField(
                        value = subject,
                        onValueChange = {  subject=it }, // Actualizar el valor
                        label = { Text("Subject") },
                        singleLine = true,
                        modifier = Modifier
                            .width(330.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    // Input para Sucursal
                    TextField(
                        value = branch,
                        onValueChange = { branch=it}, // Actualizar el valor
                        label = { Text("Branch") },
                        singleLine = true,
                        modifier = Modifier
                            .width(330.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    // Input para Mensaje
                    TextField(
                        value = message,
                        onValueChange = {message=it}, // Actualizar el valor
                        label = { Text("Message") },
                        maxLines = 5,
                        modifier = Modifier
                            .width(330.dp)
                            .height(200.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                }

                // Botón de Enviar
                Button(
                    onClick = { /* Acciones al presionar el botón */ },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(200.dp)
                ) {
                    Text(text = "Send Message")
                }
            }
        }
    )
}

