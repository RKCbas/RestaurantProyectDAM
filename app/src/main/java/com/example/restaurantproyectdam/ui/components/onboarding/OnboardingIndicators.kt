package com.example.restaurantproyectdam.ui.components.onboarding


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPageIndicator(
    pagerState: PagerState,
    pageCount: Int
){
    Row(
        horizontalArrangement = Arrangement.Center
    ){
        repeat(pageCount){iteration ->
            val color : Color = if(pagerState.currentPage==iteration) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer
            Box(modifier = Modifier
                .padding(horizontal = 4.dp)
                .clip(CircleShape)
                .background(color)
                .size(8.dp)
            )
        }
    }

}