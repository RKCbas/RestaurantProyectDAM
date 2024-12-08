package com.example.restaurantproyectdam.ui.screens


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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.projecto1.data.controller.LoginState
import com.example.projecto1.data.controller.LoginViewModel
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.data.controller.UserIdViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    userIdViewModel: UserIdViewModel,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {

    val windowHeight = currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass
    val windowWidth = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

    val loginState by viewModel.loginState.collectAsState()
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    if (windowWidth == WindowWidthSizeClass.COMPACT) {
        PortraitLogin(
            navController = navController,
            email = email,
            onEmailChange = { email = it },
            password = password,
            onPasswordChange = { password = it },
            loginState = loginState,
            onLoginClick = { viewModel.login(email, password) },
            userIdViewModel
        )
    } else if (windowHeight == WindowHeightSizeClass.COMPACT) {
        LandscapeLogin(
            navController = navController,
            email = email,
            onEmailChange = { email = it },
            password = password,
            onPasswordChange = { password = it },
            loginState = loginState,
            onLoginClick = { viewModel.login(email, password) },
            userIdViewModel
        )
    } else {
        LandscapeLogin(
            navController = navController,
            email = email,
            onEmailChange = { email = it },
            password = password,
            onPasswordChange = { password = it },
            loginState = loginState,
            onLoginClick = { viewModel.login(email, password) },
            userIdViewModel
        )
    }
}

@Composable
fun LandscapeLogin(
    navController: NavController,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    loginState: LoginState,
    onLoginClick: () -> Unit,
    userIdViewModel: UserIdViewModel
) {
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
            Text(
                text = "WELCOME BACK!",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp
            )
            Text(
                text = "Log in to your account",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Light,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = email,
                onValueChange = onEmailChange,
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.width(330.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = password,
                onValueChange = onPasswordChange,
                singleLine = true,
                label = { Text(text = "Password") },
                modifier = Modifier.width(330.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { onLoginClick() },
                modifier = Modifier
                    .width(200.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Log In")
            }

            when (loginState) {
                is LoginState.Idle -> {}

                is LoginState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                is LoginState.Success -> {
                    LaunchedEffect(Unit) {
                        userIdViewModel.UpdateUserId(loginState.user.user_id)
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                }

                is LoginState.Error -> {
                    Text(
                        text = loginState.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }

            BottomElement(navController)

        }
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

    }


}

@Composable
fun PortraitLogin(
    navController: NavController,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    loginState: LoginState,
    onLoginClick: () -> Unit,
    userIdViewModel: UserIdViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        TopElement(navController)
        Spacer(modifier = Modifier.height(40.dp))
        UserInputs(
            navController,
            email,
            onEmailChange,
            password,
            onPasswordChange,
            loginState,
            onLoginClick,
            userIdViewModel
        )
        BottomElement(navController)


    }


}

@Composable
fun TopElement(navController: NavController) {
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
            text = "WELCOME BACK!",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            fontSize = 45.sp
        )
        Text(
            text = "Log in to your account",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 20.sp
        )
    }

}


@Composable
fun UserInputs(
    navController: NavController,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    loginState: LoginState,
    onLoginClick: () -> Unit,
    userIdViewModel: UserIdViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(430.dp),
        verticalArrangement = Arrangement.SpaceEvenly

    ) {
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

        Button(
            onClick = { onLoginClick() },
            modifier = Modifier
                .width(200.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Log In")
        }

        when (loginState) {
            is LoginState.Idle -> {}

            is LoginState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            is LoginState.Success -> {
                LaunchedEffect(Unit) {
                    userIdViewModel.UpdateUserId(loginState.user.user_id)
                    println(userIdViewModel.userId)
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            }

            is LoginState.Error -> {
                Text(
                    text = loginState.message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }

    }
}


@Composable
fun BottomElement(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Don't have any account yet?",
            modifier = Modifier.align(Alignment.CenterVertically),
            color = MaterialTheme.colorScheme.onSurface,
        )
        TextButton(onClick = { navController.navigate("register") }) {
            Text(text = "Sign Up", fontSize = 17.sp)
        }

    }

}


