package com.example.restaurantproyectdam.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.data.model.OrderCardModel
import com.example.restaurantproyectdam.data.model.OrderProduct
import com.example.restaurantproyectdam.data.model.ProductModel
import com.example.restaurantproyectdam.ui.components.BottomBar
import com.example.restaurantproyectdam.ui.components.Header
import com.example.restaurantproyectdam.ui.components.SearchButton
import com.example.restaurantproyectdam.ui.components.ordercomponents.OrderCard
import com.example.restaurantproyectdam.ui.components.ordercomponents.OrderTabs
import kotlinx.coroutines.launch

@SuppressLint("Range")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen(navController: NavController) {
    Scaffold (
        //color = Color.White
        bottomBar={ BottomBar(navController = navController) },

    ) { innerPadding->
        Column(
            modifier = Modifier.padding(innerPadding)
        ){
            Content(navController)
        }
    }
}

@SuppressLint("Range")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun Content(navController: NavController){
    //VARIABLES
    //Trigger suspend function that will animate the scroll inside the horizontal pager
    val scope = rememberCoroutineScope()

    //pager states should passed directly to the horizontal pager composable to control its behavior
    val pagerState = rememberPagerState(pageCount = { OrderTabs.entries.size })

    //A selected tab index should be used to control which tab is currently selected
    //Also which data to show inside the horizontal pager as well
    val selectedTabIndex = remember {
        derivedStateOf {  //So we can avoid unnecessary recompositions
            // and improve performance in responsiveness in our app
            pagerState.currentPage
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
        //.padding(top = 100.dp) // to leave the space for the top bar
    ) {
        Header("ORDERS")
        TabRow(
            selectedTabIndex = selectedTabIndex.value,
            modifier = Modifier.fillMaxWidth()
        ) {
            //Loop through all enum entries
            OrderTabs.entries.forEachIndexed { index, currentTab ->
                Tab(
                    selected = selectedTabIndex.value == index, // Check if this tab is selected
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.outline,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(currentTab.ordinal)
                        }
                    },
                    text = { Text(text = currentTab.text) },
                    icon = {
                        Icon(
                            imageVector = if (selectedTabIndex.value == index)
                                currentTab.selectedIcon else currentTab.unselectedIcon,
                            contentDescription = "Tab Icon"
                        )
                    }
                )
            }

        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            val orderProductsArray = arrayOf(
                OrderProduct(
                    amount = 1,
                    orderProduct =
                    ProductModel(
                        1,
                        "Tempura",
                        "Tempura is a Japanese dish of lightly battered and deep-fried vegetables and seafood that's known for its unique crispy, non-greasy texture. ",
                        200.00f,
                        painterResource(R.drawable.tempura),
                        1
                    )
                ),
                OrderProduct(
                    amount = 2,
                    orderProduct =
                    ProductModel(
                        2,
                        "Dango",
                        "Dango is a Japanese dessert made from rice flour and glutinous rice flour, and is a popular confectionery in the country",
                        100.00f,
                        painterResource(R.drawable.dango),
                        2
                    )
                )

            )
            val orderCardsArray = arrayOf(
                OrderCardModel(id = 1, status = true, orderProducts = orderProductsArray),
                OrderCardModel(id = 2, status = false, orderProducts = orderProductsArray),
                OrderCardModel(id = 3, status = true, orderProducts = orderProductsArray)
            )

            LazyColumn {
                items(orderCardsArray){ orderCard ->
                    OrderCard(id = orderCard.id, status = orderCard.status, products = orderCard.orderProducts, navController)
                }
            }
        }
    }

}

