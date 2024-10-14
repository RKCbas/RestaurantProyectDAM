package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.restaurantproyectdam.ui.components.BottomBar
import com.example.restaurantproyectdam.ui.components.SearchButton

@Composable
fun ProfileScreen (navController: NavController) {
    Scaffold (
        //color = Color.White
        bottomBar={ BottomBar(navController = navController) },

    ) { innerPadding->
        Column(
            modifier = Modifier.padding(innerPadding)
        ){
            Content(navController)
        }
    }
}

@Composable
private fun Content(navController: NavController){
    Column {
        TextButton(onClick = {navController.navigate("register")}) {
            Text(text="Hola")

            }
        }

    }
