package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AdminOrdersScreen (navController: NavController){
    Column {
        Text(text = "This is Admin Orders Screen")
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
                    onClick = { navController.navigate("login") },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(text = "Login", color = Color.White)
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
                    onClick = { navController.navigate("adminProducts") },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(text = "Admin Products", color = Color.White)
                }
            }

        }

    }
}