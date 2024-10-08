package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Preview
@Composable
fun PreviewLoginScreen(){
    LoginScreen(navController = rememberNavController())
}

@Composable
fun LoginScreen (navController: NavController){
    Column (
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "",
                modifier = Modifier.align(Alignment.TopStart)
            )
            Text(text = "Bienvenido de vuelta", modifier = Modifier.align(Alignment.BottomCenter))
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Inicia sesion en tu cuenta",
                modifier = Modifier.align(Alignment.BottomCenter)
            )


        }

            /*
        Text(text = "This is Login Screen")
        LazyRow {
            item {
                Button(
                    onClick = { navController.navigate("home") },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(text = "Home", color = Color.White)
                }
            }
            item {
                Button(
                    onClick = { navController.navigate("register") },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(text = "Register", color = Color.White)
                }
            }
            item {
                Button(
                    onClick = { navController.navigate("orders") },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(text = "Orders", color = Color.White)
                }
            }
            item {
                Button(
                    onClick = { navController.navigate("adminOrders") },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(text = "Admin Orders", color = Color.White)
                }
            }
            item {
                Button(
                    onClick = { navController.navigate("adminProducts") },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(text = "Admin Products", color = Color.White)
                }
            }

        }*/

    }
}
