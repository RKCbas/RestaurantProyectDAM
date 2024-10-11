package com.example.restaurantproyectdam.ui.screens

import android.content.res.Resources.Theme
import android.graphics.Paint.Align
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.restaurantproyectdam.R


@Preview(showBackground=true)
@Composable
fun PreviewLoginScreen(){
    LoginScreen(navController = rememberNavController())
}

@Composable
fun LoginScreen (navController: NavController) {
    //Columna pricipal donde se muestra el contenido
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        TopElement()

        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Inicia sesion en tu cuenta",
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.height(80.dp))
        UserInputs()
        BottomElement()
    }

}

@Composable
fun TopElement() {
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
            )
            Icon(
                painter=painterResource(R.drawable.logo_fuji),
                contentDescription ="logo",
                modifier= Modifier
                    //.size(150.dp)
                    .width(300.dp)
                    .height(150.dp)
                    .align(Alignment.Center),
                tint = colorResource(R.color.login_top_shape)// Set the color you want here
            )

            Text(
                text = "¡BIENVENIDO DE VUELTA!",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(top = 60.dp),
                style = MaterialTheme.typography.titleLarge,
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
            label = { Text("Correo Electrónico") },
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
            label = { Text("Contraseña") },
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
            Text(text = "¿Olvidaste tu contraseña?")
        }
        Button(onClick = {}, modifier = Modifier
            .width(270.dp)
            .align(Alignment.CenterHorizontally)
            .padding(top = 70.dp)
        ){
            Text(text = "Iniciar Sesion")
        }
    }
}

//Link a registro
@Composable
fun BottomElement(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Text(text = "¿No tienes una cuenta?", modifier = Modifier.align(Alignment.CenterVertically) )
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Registrate",   fontSize = 17.sp)
        }

    }

}



