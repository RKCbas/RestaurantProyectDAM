package com.example.restaurantproyectdam.ui.screens

import android.content.res.Resources.Theme
import android.graphics.Paint.Align
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import androidx.graphics.shapes.RoundedPolygon
//import androidx.graphics.shapes.toPath
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.restaurantproyectdam.R
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen(navController = rememberNavController())
}

@Composable
fun LoginScreen (navController: NavController) {
    //Columna pricipal donde se muestra el contenido
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        TopElement(navController)

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Login",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,

            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.height(80.dp))
        UserInputs()
        BottomElement(navController)
    }

}



@Composable
fun TopElement(navController: NavController) {
    // Controlamos la parte superior del column
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 30.dp, top = 30.dp)
                    .clickable { navController.navigate("home") },
                tint = MaterialTheme.colorScheme.onSurface,

                )
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.logo_fuji),
                contentDescription = "Custom SVG Icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
                    .alpha(0.5f)
                ,
            )
            /*Icon(
                painter=painterResource(R.drawable.logo_fuji),
                contentDescription ="logo",
                modifier= Modifier
                    //.size(150.dp)
                    .width(300.dp)
                    .height(150.dp)
                    .align(Alignment.Center),
                tint = colorResource(R.color.login_top_shape)// Set the color you want here
            )*/

            Text(
                text = "¡WELCOM BACK!",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(top = 60.dp),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )

        }



}



//Campos de texto, link de recuperar contraseña y boton
@Composable
fun UserInputs(){
    var text by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier
                .width(330.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            label = { Text("Password") },
            modifier = Modifier
                .width(330.dp)
                .align(Alignment.CenterHorizontally),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        TextButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
            ) {
            Text(text = "Forgot your password?")
        }
        val snackState = remember{ SnackbarHostState() }
        val snackScope = rememberCoroutineScope()

        SnackbarHost(hostState = snackState, Modifier)

        fun launchSnackBar(){
            snackScope.launch { snackState.showSnackbar("Log in successfull") }
        }
        Button(onClick = {launchSnackBar()}, modifier = Modifier
            .width(270.dp)
            .align(Alignment.CenterHorizontally)
            .padding(top = 70.dp)
        ){
            Text(text = "Log In")
        }
    }
}

//Link a registro
@Composable
fun BottomElement(navController: NavController){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = "Don't have any account yet?",
            modifier = Modifier.align(Alignment.CenterVertically),
            color = MaterialTheme.colorScheme.onSurface,
            )
        TextButton(onClick = {navController.navigate("register")}) {
            Text(text = "Sign Up",   fontSize = 17.sp)
        }

    }

}



