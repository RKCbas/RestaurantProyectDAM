package com.example.restaurantproyectdam.ui.components.homecomponents

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.restaurantproyectdam.data.model.DishEntity
import com.example.restaurantproyectdam.data.model.createArrayProducts

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerScreen(navController : NavController,userId: Int?, dishes: List<DishEntity>){

    val limitDishes = dishes.take(5)

    val pagerState : PagerState = rememberPagerState(pageCount = { limitDishes.size})
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        HorizontalPager(state=pagerState) { pageIndex->
            SinglePage(page = limitDishes[pageIndex], navController)
        }

        Spacer(modifier=Modifier.size(16.dp))

        PageIndicator(pagerState = pagerState, pageCount = limitDishes.size)
    }
}