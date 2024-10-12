package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
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
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ){
        TopElementsRegister(navController)
        MidElementsRegister(navController)
        BotElementsRegister(navController)
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
                .padding(top = 28.dp, start = 23.dp)
        )
        Icon(
            painter=painterResource(R.drawable.logo_fuji),
            contentDescription ="logo",
            modifier= Modifier
                .width(300.dp)
                .height(150.dp)
                .align(Alignment.CenterHorizontally),
            tint = colorResource(R.color.teal_700)// Set the color you want here
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Registrate",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )
        Text(
            text = "Crea tu nueva cuenta",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 13.sp
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
            label = { Text("Nombre de Usuario") },
            singleLine = true,
            modifier = Modifier
                .width(330.dp)
                .align(Alignment.CenterHorizontally)
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            singleLine = true,
            modifier = Modifier
                .width(330.dp)
                .align(Alignment.CenterHorizontally)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            label = { Text(text = "Contraseña") },
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
            label = { Text(text = "Confirma tu contraseña") },
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

        ){
            Text(text = "Registrarse")
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

@Composable
fun BotElementsRegister(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            HorizontalDivider(
                thickness = 2.dp,
                modifier = Modifier
                    .width(150.dp)
                    .align(Alignment.CenterVertically)
                    .padding(start = 5.dp, end = 5.dp),
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(text = "O registrate con", style = MaterialTheme.typography.bodySmall)
            HorizontalDivider(
                thickness = 2.dp,
                modifier = Modifier
                    .width(150.dp)
                    .align(Alignment.CenterVertically)
                    .padding(start = 5.dp, end = 5.dp),
                color = MaterialTheme.colorScheme.tertiary
            )


        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Image(
                painter = painterResource(R.drawable.facebook_logo),
                contentDescription = "facebook logo",
                modifier = Modifier
                    .size(60.dp),
            )
            Image(
                painter = painterResource(R.drawable.google_logo),
                contentDescription = "google logo",
                modifier = Modifier
                    .size(60.dp)
                    .padding(10.dp)
            )
        }

        Row (
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "¿Ya tienes una cuenta?",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
            TextButton(
                onClick = {navController.navigate("login")}) {
                Text(text = "Inicia Sesión")
    
}
        }
    }

}
