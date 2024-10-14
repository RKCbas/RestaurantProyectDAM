package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.ui.components.onboarding.OnboardingPageIndicator
import com.example.restaurantproyectdam.ui.components.onboarding.OnboardingScreen


@Composable
fun WelcomeScreen(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Image(
            painter = painterResource(
                id = R.drawable.onboardingbackground2
            ),
            contentDescription = null,
            contentScale = ContentScale.FillBounds

        )
        Column(){
            OnboardingScreen(navController = navController)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview(){
    WelcomeScreen(navController = rememberNavController())
}