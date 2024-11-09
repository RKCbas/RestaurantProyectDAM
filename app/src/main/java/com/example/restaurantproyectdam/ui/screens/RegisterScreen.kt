package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.restaurantproyectdam.R

@Preview
@Composable
fun PreviewRegisterScreen(){
    RegisterScreen(navController = rememberNavController())
}

@Composable
fun RegisterScreen (navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = MaterialTheme.colorScheme.surface)
    ){
        TopElementsRegister(navController)
        MidElementsRegister(navController)
    }
}

@Composable
fun TopElementsRegister(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Icon(
            Icons.Filled.ArrowBack,
            contentDescription = "Icon of arrow back",
            modifier = Modifier
                .clickable { navController.navigate("home") }
                .padding(top = 28.dp, start = 23.dp),
            tint = MaterialTheme.colorScheme.onSurface,

            )

        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.logo_fuji),
            contentDescription = "Custom SVG Icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
                .alpha(0.5f)
            ,
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Register",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            fontSize = 60.sp
        )
        Text(
            text = "Create a new account",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,

            fontSize = 20.sp
        )

    }
}

@Composable
fun MidElementsRegister(navController: NavController){
    var username by remember{ mutableStateOf("") }
    var email by remember{ mutableStateOf("")}
    var password by rememberSaveable { mutableStateOf("")}
    var confirmPassword by rememberSaveable { mutableStateOf("")}
    var showSnackbar by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(430.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("User name") },
            singleLine = true,
            modifier = Modifier
                .width(330.dp)
                .align(Alignment.CenterHorizontally)
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier
                .width(330.dp)
                .align(Alignment.CenterHorizontally)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            label = { Text(text = "Password") },
            modifier = Modifier
                .width(330.dp)
                .align(Alignment.CenterHorizontally),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            singleLine = true,
            label = { Text(text = "Confirm your password") },
            modifier = Modifier
                .width(330.dp)
                .align(Alignment.CenterHorizontally),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Button(
            onClick = {
                if (password != confirmPassword) {
                    showSnackbar = true
                }
                else{
                    navController.navigate("home")
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(200.dp)
                .padding(top=20.dp)

        ){
            Text(text = "Sign Up")
        }

    }

    if (showSnackbar){
        Snackbar(
            action = {
                TextButton(onClick = {showSnackbar=false}) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Cerrar icono")
                    
                }

            },
            modifier = Modifier
                .padding(8.dp)
        ){
            Text(text = "Las contraseñas no coinciden")
        }
    }
}