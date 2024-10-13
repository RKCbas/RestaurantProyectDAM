package com.example.restaurantproyectdam.ui.screens

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.restaurantproyectdam.data.model.ProfileOptionsModel
import com.example.restaurantproyectdam.ui.components.BottomBar
import com.example.restaurantproyectdam.ui.components.SearchButton
import java.lang.StackWalker.Option

@Composable
fun ProfileScreen (navController: NavController) {
    Scaffold (
        //color = Color.White
        bottomBar={ BottomBar(navController = navController) },

    ) { innerPadding->
        Column(
            modifier = Modifier
                .padding(innerPadding)

        ){
            Content(navController)
        }
    }
}

@Composable
private fun Content(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ){
        TopContentProfile()
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp) // Aumentar padding del separador
        )
        Spacer(modifier = Modifier.height(20.dp))
        MidContentProfile()
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp) // Aumentar padding del separador
        )
        Spacer(modifier = Modifier.height(20.dp))
        BotContentProfile(navController)

    }
}

@Composable
private fun TopContentProfile(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(80.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Person logo",
                modifier = Modifier
                    //.padding(top = 15.dp, start = 15.dp, bottom = 15.dp)
                    .size(50.dp)
            )
            Column(
                modifier=Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly
            ){
                Text(
                    text ="Armando Vallejo",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "juanarmandovh20@gmail.com",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text="Sesion iniciada con Google",
                    style = MaterialTheme.typography.bodySmall
                )

            }

        }
    }
}

@Composable
private fun MidContentProfile(){
    var options = listOf(
        ProfileOptionsModel(1,"Mi perfil",Icons.Filled.Person),
        ProfileOptionsModel(2,"Mis direcciones",Icons.Filled.LocationOn),
        ProfileOptionsModel(3,"Mis formas de pago",Icons.Filled.Email),
        ProfileOptionsModel(4,"Mis pedidos",Icons.Filled.ShoppingCart),
        ProfileOptionsModel(5,"Terminos y condiciones",Icons.Filled.Check),
        ProfileOptionsModel(6,"Calificanos en PlayStore",Icons.Filled.Favorite),
        ProfileOptionsModel(7,"Contactanos",Icons.Filled.Edit)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(options){option->
            OptionCard(title=option.title, icon=option.icon)
        }

    }
}

@Composable
private fun OptionCard(title: String, icon: ImageVector) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), // Aumentar padding externo
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp), // Aumentar padding interno
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically // Alinear contenido verticalmente
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp) // Aumentar tamaño del icono
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium, // Aumentar el estilo del texto
                    modifier = Modifier
                        .padding(start = 10.dp) // Aumentar padding entre el ícono y el texto
                )
            }
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = null,
                modifier = Modifier.size(20.dp) // Aumentar tamaño del ícono de flecha
            )
        }
    }
}

@Composable
private fun BotContentProfile(navController:NavController){
    Column(
        modifier=Modifier
            .fillMaxWidth(),

    ) {
        Button(
            onClick = { navController.navigate("login") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(300.dp)
        )
        {
            Text(text = "Iniciar Sesion", color = Color.Red)
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(300.dp)
        )
        {
            Text(text = "Eliminar Cuenta", color = Color.Red)
        }

    }

}


