package com.example.restaurantproyectdam.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.data.controller.LoginState
import com.example.restaurantproyectdam.data.controller.LoginViewModel
import com.example.restaurantproyectdam.data.controller.UpdateState
import com.example.restaurantproyectdam.data.controller.UpdateViewModel
import com.example.restaurantproyectdam.data.controller.UserIdViewModel
import com.example.restaurantproyectdam.data.model.AddToCartModelRequest
import kotlinx.coroutines.launch

@Composable
fun CustomProfileScreen(
    navController: NavController,
    userIdViewModel: UserIdViewModel,
    viewModel: UpdateViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    loginViewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    var name by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    val updateState by viewModel.updateState.collectAsState()


    LaunchedEffect(Unit) {
        loginViewModel.getUser(userIdViewModel.userId!!) { response ->
            if (response.isSuccessful) {
                name = response.body()?.name.toString() ?: ""
                password = ""
                phone = response.body()?.phone.toString() ?: ""


            } else {
                println("something went wrong")
            }
        }
    }


    println("id is {${userIdViewModel.userId}}")
    println("name is {}")
    // State variables to hold user input



    // Main content of the screen
    ContentProfile(
        navController = navController,
        name = name,
        onNameChange = { name = it },
        password = password,
        onPasswordChange = { password = it },
        phone= phone,
        onPhoneChange = { phone = it},
        updateState = updateState,
        onUpdateClick = { viewModel.update(userIdViewModel.userId!!,name,password, phone) },


        )

}

@Composable
private fun ContentProfile(
    navController: NavController,
    name: String,
    onNameChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    phone: String,
    onPhoneChange: (String) -> Unit,
    updateState: UpdateState,
    onUpdateClick: () -> Unit,


){



    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ){
        Icon(
            Icons.Filled.ArrowBack,
            contentDescription = "Icon of arrow back",
            modifier = Modifier
                .clickable { navController.popBackStack() }
                .padding(top = 30.dp, start = 30.dp)
                .size(28.dp),
            tint = MaterialTheme.colorScheme.onSurface,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                Icons.Filled.Person,
                "Profile",
                tint = MaterialTheme.colorScheme.tertiary, // Optional color tint
                modifier = Modifier.size(200.dp) // Optional size modifier
            )
            // Name TextField
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )

            /*TextField(
                value = email,
                onValueChange = onEmailChange,
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.width(330.dp)
            )*/

            Spacer(modifier = Modifier.height(16.dp))

            // password TextField
            OutlinedTextField(
                value = password,
                onValueChange = onPasswordChange,
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Phone Number TextField
            OutlinedTextField(
                value = phone,
                onValueChange = onPhoneChange,
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Save Button
            Button(
                //onClick = { navController.navigate("profile")},
                onClick = {
                    snackScope.launch {
                        when {
                            name.isBlank() || password.isBlank() || phone.isBlank() -> {
                                snackState.showSnackbar("Please fill all fields")
                            }

                            password.length < 6 -> {
                                snackState.showSnackbar("The passwords must be longer than 6 characters")
                            }



                            else -> {
                                onUpdateClick()
                            }
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                //colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.errorContainer)
            ) {
                Text("Save")
            }

            when (updateState) {
                is UpdateState.Idle -> {}

                is UpdateState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                is UpdateState.Success -> {
                    LaunchedEffect(Unit) {
                        snackState.showSnackbar("User successfully updated")
                    }
                }

                is UpdateState.Error -> {
                    Text(
                        text = updateState.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomProfileScreenPreview() {
    //CustomProfileScreen(rememberNavController())
}
