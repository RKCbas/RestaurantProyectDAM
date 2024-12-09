package com.example.restaurantproyectdam.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.restaurantproyectdam.data.controller.LoginViewModel
import com.example.restaurantproyectdam.data.controller.UserIdViewModel
import com.example.restaurantproyectdam.data.model.ProfileOptionsModel
import com.example.restaurantproyectdam.ui.components.BottomBar

@Composable
fun ProfileScreen(navController: NavController, userIdViewModel: UserIdViewModel, ) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Content(navController,userIdViewModel)
        }
    }
}


@Composable
private fun Content(navController: NavController,userIdViewModel: UserIdViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        TopContentProfile(userIdViewModel)
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp) // Aumentar padding del separador
        )
        Spacer(modifier = Modifier.height(20.dp))
        MidContentProfile(navController)
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp) // Aumentar padding del separador
        )
        Spacer(modifier = Modifier.height(20.dp))
        BotContentProfile(navController,userIdViewModel)
    }
}

@Composable
private fun TopContentProfile(
    userIdViewModel: UserIdViewModel,
    loginViewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(80.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.primary,

        )
    ) {
        var name by rememberSaveable { mutableStateOf("") }
        var phone by rememberSaveable { mutableStateOf("") }
        var email by rememberSaveable { mutableStateOf("") }
        LaunchedEffect(Unit) {
            loginViewModel.getUser(userIdViewModel.userId!!) { response ->
                if (response.isSuccessful) {
                    name = response.body()?.name.toString() ?: ""
                    phone = response.body()?.phone.toString() ?: ""
                    email = response.body()?.email.toString() ?:""

                } else {
                    println("something went wrong")
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Person logo",
                modifier = Modifier
                    .size(50.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = email,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = phone,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
private fun MidContentProfile(navController: NavController) {
    val context = LocalContext.current

    val options = listOf(
        ProfileOptionsModel(1, "My Profile", Icons.Filled.Person, "edit_profile"),
        //ProfileOptionsModel(2, "My Adresses", Icons.Filled.LocationOn, "addresses"),
        //ProfileOptionsModel(3, "My Payment Methods", Icons.Filled.Email, "payment_methods"),
        ProfileOptionsModel(4, "My Orders", Icons.Filled.ShoppingCart, "orders"),
        ProfileOptionsModel(5, "Rate Us on PlayStore", Icons.Filled.Favorite, "rate_us"),
        ProfileOptionsModel(6, "Contact Us", Icons.Filled.Edit, "contact_us")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(options) { option ->
            OptionCard(
                title = option.title,
                icon = option.icon,
                onClick = {
                    if (option.id == 5) {
                        openPlayStore(context) // Llamamos a la función para abrir Play Store
                    } else {
                        navController.navigate(option.route) // De lo contrario, navegamos a la pantalla correspondiente
                    }
                }
            )
        }
    }
}

fun openPlayStore(context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store")) // URL de la Play Store
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    try {
        context.startActivity(intent) // Iniciar el Intent para abrir la Play Store
    } catch (e: Exception) {
        // Manejo de excepciones si no se puede abrir la Play Store
    }
}

@Composable
private fun OptionCard(title: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
private fun BotContentProfile(navController: NavController,userIdViewModel: UserIdViewModel,) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Button(
            onClick = {
                    userIdViewModel.clearUserId()
                    userIdViewModel.clearCartId()
                    navController.navigate("login")
                      },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(300.dp)
        ) {
            Text(text = "Log out")
        }
        /*Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(300.dp)
        ) {
            Text(text = "Delete Account")
        }*/
    }
}
