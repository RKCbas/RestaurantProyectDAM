package com.example.restaurantproyectdam.ui.components.onboarding

import androidx.annotation.DrawableRes
import com.example.restaurantproyectdam.R


data class OnboardingData(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)
val OnboardingPages = listOf(
    OnboardingData(
        title = "JAPANESE",
        description ="Restaurant",
        image = R.drawable.sushi
    ),
    OnboardingData(
        title = "VARIETY",
        description = "Sushi, tempura, Ramen, Onigiri and more",
        image = R.drawable.onigiri
    ),
    OnboardingData(
        title = "QUALITY",
        description = "A place where quality can be found",
        image = R.drawable.ramen
    )


)