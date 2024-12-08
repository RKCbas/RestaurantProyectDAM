package com.example.restaurantproyectdam.ui.screens

import android.graphics.Paint.Align
import android.widget.Toast
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.example.restaurantproyectdam.data.controller.RegisterState
import com.example.restaurantproyectdam.data.controller.RegisterViewModel
import kotlinx.coroutines.launch


@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val windowHeight = currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass
    val windowWidth = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

    var username by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    val registerState by viewModel.registerState.collectAsState()

    if (windowWidth == WindowWidthSizeClass.COMPACT) {
        PortraitRegister(
            navController = navController,
            username = username,
            onUsernameChange = { username = it },
            email = email,
            onEmailChange = { email = it },
            password = password,
            onPasswordChange = { password = it },
            confirmPassword = confirmPassword,
            onConfirmPasswordChange = { confirmPassword = it },
            registerState = registerState,
            onRegisterClick = {
                viewModel.register(
                    email = email,
                    password = password,
                    name = username
                )
            }
        )
    } else if (windowHeight == WindowHeightSizeClass.COMPACT) {
        LandscapeRegister(
            navController = navController,
            username = username,
            onUsernameChange = { username = it },
            email = email,
            onEmailChange = { email = it },
            password = password,
            onPasswordChange = { password = it },
            confirmPassword = confirmPassword,
            onConfirmPasswordChange = { confirmPassword = it },
            registerState = registerState,
            onRegisterClick = {
                viewModel.register(
                    email = email,
                    password = password,
                    name = username
                )
            }
        )
    } else {
        LandscapeRegister(
            navController = navController,
            username = username,
            onUsernameChange = { username = it },
            email = email,
            onEmailChange = { email = it },
            password = password,
            onPasswordChange = { password = it },
            confirmPassword = confirmPassword,
            onConfirmPasswordChange = { confirmPassword = it },
            registerState = registerState,
            onRegisterClick = {
                viewModel.register(
                    email = email,
                    password = password,
                    name = username
                )
            }
        )
    }
}

@Composable
fun PortraitRegister(
    navController: NavController,
    username: String,
    onUsernameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    registerState: RegisterState,
    onRegisterClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        TopElementsRegister(navController)
        MidElementsRegister(
            navController,
            username,
            onUsernameChange,
            email,
            onEmailChange,
            password,
            onPasswordChange,
            confirmPassword,
            onConfirmPasswordChange,
            registerState = registerState,
            onRegisterClick = onRegisterClick
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Do you have an account?",
                modifier = Modifier.align(Alignment.CenterVertically),
                color = MaterialTheme.colorScheme.onSurface,
            )
            TextButton(onClick = { navController.popBackStack() }) {
                Text(text = "Login", fontSize = 17.sp)
            }

        }

    }
}

@Composable
fun TopElementsRegister(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            Icons.Filled.ArrowBack,
            contentDescription = "Icon of arrow back",
            modifier = Modifier
                .clickable { navController.popBackStack() }
                .padding(top = 28.dp, start = 23.dp),
            tint = MaterialTheme.colorScheme.onSurface,
        )

        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.logo_fuji),
            contentDescription = "Custom SVG Icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.5f),
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Register",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            fontSize = 60.sp
        )
        Text(
            text = "Create a new account",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 20.sp
        )
    }
}

@Composable
fun MidElementsRegister(
    navController: NavController,
    username: String,
    onUsernameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    registerState: RegisterState,
    onRegisterClick: () -> Unit
) {
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(430.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        TextField(
            value = username,
            onValueChange = onUsernameChange,
            label = { Text("User name") },
            singleLine = true,
            modifier = Modifier
                .width(330.dp)
                .align(Alignment.CenterHorizontally)
        )
        TextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier
                .width(330.dp)
                .align(Alignment.CenterHorizontally)
        )
        TextField(
            value = password,
            onValueChange = onPasswordChange,
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
            onValueChange = onConfirmPasswordChange,
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
                snackScope.launch {
                    when {
                        username.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank() -> {
                            snackState.showSnackbar("Please fill all fields")
                        }

                        password.length < 6 && confirmPassword.length < 6 -> {
                            snackState.showSnackbar("The passwords must be longer than 6 characters")
                        }

                        password != confirmPassword -> {
                            snackState.showSnackbar("The passwords are not equal")
                        }

                        else -> {
                            onRegisterClick()
                        }
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(200.dp)
                .padding(top = 20.dp)
        ) {
            Text(text = "Sign Up")
        }
        when (registerState) {
            is RegisterState.Idle -> {

            }

            is RegisterState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            is RegisterState.Success -> {
                val context = LocalContext.current
                LaunchedEffect(Unit) {
                    Toast.makeText(context, "Usuario ${registerState.registerUser.name} creado exitosamente", Toast.LENGTH_SHORT).show()
                    navController.navigate("home/${registerState.registerUser.user_id}") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            }

            is RegisterState.Error -> {
                val context = LocalContext.current
                Toast.makeText(context, registerState.message, Toast.LENGTH_SHORT).show()
            }

        }

    }
}

@Composable
fun LandscapeRegister(
    navController: NavController,
    username: String,
    onUsernameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    registerState: RegisterState,
    onRegisterClick: () -> Unit
) {
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
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
                modifier = Modifier.fillMaxWidth()
            )
        }
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
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
            item {
                TextField(
                    value = username,
                    onValueChange = onUsernameChange,
                    label = { Text("User name") },
                    singleLine = true,
                    modifier = Modifier.width(330.dp)
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp)) // Espacio adicional
            }
            item {
                TextField(
                    value = email,
                    onValueChange = onEmailChange,
                    label = { Text("Email") },
                    singleLine = true,
                    modifier = Modifier.width(330.dp)
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp)) // Espacio adicional
            }
            item {
                TextField(
                    value = password,
                    onValueChange = onPasswordChange,
                    singleLine = true,
                    label = { Text(text = "Password") },
                    modifier = Modifier.width(330.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp)) // Espacio adicional
            }
            item {
                TextField(
                    value = confirmPassword,
                    onValueChange = onConfirmPasswordChange,
                    singleLine = true,
                    label = { Text(text = "Confirm your password") },
                    modifier = Modifier.width(330.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }
            item {
                Spacer(modifier = Modifier.height(24.dp)) // Espacio adicional para el botón
            }
            item {
                Button(
                    onClick = {
                        snackScope.launch {
                            when {
                                username.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank() -> {
                                    snackState.showSnackbar("Please fill all fields")
                                }

                                password.length < 6 && confirmPassword.length < 6 -> {
                                    snackState.showSnackbar("The passwords must be longer than 6 characters")
                                }

                                password != confirmPassword -> {
                                    snackState.showSnackbar("The passwords are not equal")
                                }

                                else -> {
                                    onRegisterClick()
                                }
                            }
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
                when (registerState) {
                    is RegisterState.Idle -> {

                    }

                    is RegisterState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }

                    is RegisterState.Success -> {
                        val context = LocalContext.current
                        LaunchedEffect(Unit) {
                            Toast.makeText(context, "Usuario ${registerState.registerUser.name} creado exitosamente", Toast.LENGTH_SHORT).show()
                            navController.navigate("home/${registerState.registerUser.user_id}") {
                                popUpTo("register") { inclusive = true }
                            }
                        }
                    }

                    is RegisterState.Error -> {
                        val context = LocalContext.current
                        Toast.makeText(context, registerState.message, Toast.LENGTH_SHORT).show()
                    }

                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Do you have an account?",
                        modifier = Modifier.align(Alignment.CenterVertically),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    TextButton(onClick = { navController.popBackStack() }) {
                        Text(text = "Login", fontSize = 17.sp)
                    }

                }
            }
        }
    }
}




