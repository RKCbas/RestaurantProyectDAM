package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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

@Preview(showBackground=true)
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
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 10.dp, top = 20.dp)
            )
            Text(text = "BIENVENIDO DE VUELTA", modifier = Modifier.align(Alignment.BottomCenter))

        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Inicia sesion en tu cuenta",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
            InputTextsSamples()
            LoginButtonExample()


    }
}

@Composable
fun InputTextsSamples(){
    var text by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {


        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Correo Electrónico") },
            singleLine = true,
            modifier = Modifier
                .width(300.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            label = { Text("Enter password") },
            modifier = Modifier
                .width(300.dp)
                .align(Alignment.CenterHorizontally),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)

            ) {
            Text(text = "Has olvidado la contraseña?")
            
        }
    }
}

@Composable
fun LoginButtonExample(){
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = {}, modifier = Modifier.width(200.dp).align(Alignment.CenterHorizontally)) {
            Text(text = "Iniciar Sesion")
            
        }

    }
}



