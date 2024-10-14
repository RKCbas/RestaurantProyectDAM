package com.example.restaurantproyectdam.ui.components.onboarding


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.restaurantproyectdam.data.model.createArrayProducts
import com.example.restaurantproyectdam.ui.components.homecomponents.PageIndicator
import com.example.restaurantproyectdam.ui.components.homecomponents.ProductData
import com.example.restaurantproyectdam.ui.components.homecomponents.SinglePage
import com.example.restaurantproyectdam.ui.components.homecomponents.pages

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(navController : NavController){
    val items :List<ProductData>
    //pages.size
    val pagerState : PagerState = rememberPagerState(pageCount = { OnboardingPages.size})
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        HorizontalPager(state=pagerState) { pageIndex->
            SingleOnboarding(page = OnboardingPages[pageIndex], navController)
        }
        Spacer(modifier=Modifier.size(16.dp))
        //PreviousNextButtons(pagerState = pagerState,pages.size)
        OnboardingPageIndicator(pagerState = pagerState, pageCount = OnboardingPages.size)
    }
}