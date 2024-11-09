package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowSize
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.restaurantproyectdam.R

//@Preview
@Composable
fun PreviewPortraitScreen(){
    PortraitRegister(navController = rememberNavController())
}

@Preview(
    device = Devices.TABLET,
    showBackground = true
)
@Composable
fun PreviewLandScreen(){
    LandscapeRegister(navController = rememberNavController())
}

@Composable
fun RegisterScreen (navController: NavController){
    var windowSize = currentWindowAdaptiveInfo().windowSizeClass
    var windowHeight = currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass
    var windowWidth = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

    if(windowWidth == WindowWidthSizeClass.COMPACT){
        PortraitRegister(navController = rememberNavController())
    }
    else if(windowHeight == WindowHeightSizeClass.COMPACT){
        LandscapeRegister(navController = rememberNavController())
    }
    else{
        Text(text = "Landscape")
    }
}

@Composable
fun PortraitRegister(navController: NavController){
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
            modifier = Modifier
                .fillMaxSize()
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
                .padding(top = 20.dp)

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

@Composable
fun LandscapeRegister(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Icon(
            Icons.Filled.ArrowBack,
            contentDescription = "Icon of arrow back",
            modifier = Modifier
                .clickable { navController.navigate("home") }
                .padding(top = 30.dp, start = 30.dp)
                .size(28.dp),
            tint = MaterialTheme.colorScheme.onSurface,
        )
        // Columna para el logo en la izquierda
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.logo_fuji),
                contentDescription = "Custom SVG Icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        var username by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by rememberSaveable { mutableStateOf("") }
        var confirmPassword by rememberSaveable { mutableStateOf("") }
        var showSnackbar by remember { mutableStateOf(false) }


        // Columna desplazable para los campos de texto en la derecha
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Register",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 60.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            item {
                Text(
                    text = "Create a new account",
                    fontWeight = FontWeight.Light,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
            }
            item {
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("User name") },
                    singleLine = true,
                    modifier = Modifier
                        .width(300.dp)
                        .padding(bottom = 16.dp)
                )
            }
            item {
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    singleLine = true,
                    modifier = Modifier
                        .width(300.dp)
                        .padding(bottom = 16.dp)
                )
            }
            item {
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    singleLine = true,
                    label = { Text(text = "Password") },
                    modifier = Modifier
                        .width(300.dp)
                        .padding(bottom = 16.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }
            item {
                TextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    singleLine = true,
                    label = { Text(text = "Confirm your password") },
                    modifier = Modifier
                        .width(300.dp)
                        .padding(bottom = 16.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }
            item {
                Button(
                    onClick = {
                        if (password != confirmPassword) {
                            showSnackbar = true
                        } else {
                            navController.navigate("home")
                        }
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(top = 20.dp)
                ) {
                    Text(text = "Sign Up")
                }
            }

            item {
                if (showSnackbar) {
                    Snackbar(
                        action = {
                            TextButton(onClick = { showSnackbar = false }) {
                                Icon(imageVector = Icons.Filled.Close, contentDescription = "Cerrar icono")
                            }
                        },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Las contraseñas no coinciden")
                    }
                }
            }
        }
    }
}


