package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.restaurantproyectdam.ui.components.BottomBar
import com.example.restaurantproyectdam.ui.components.SearchButton

@Composable
fun MenuScreen (navController: NavController) {
    Scaffold (
        //color = Color.White
        bottomBar={ BottomBar(navController = navController) },
        floatingActionButton = { SearchButton(onClick = {}) }
    ) { innerPadding->
        Column(
            modifier = Modifier.padding(innerPadding)
        ){
            Content()
        }
    }
}

@Composable
private fun Content(){ //CÓDIGO DE YAHAIRA

}