package com.example.restaurantproyectdam.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.data.model.OrderProduct
import com.example.restaurantproyectdam.data.model.ProductModel
import com.example.restaurantproyectdam.ui.components.BottomBar
import com.example.restaurantproyectdam.ui.components.Header
import com.example.restaurantproyectdam.ui.components.ordercomponents.OrderCard
import com.example.restaurantproyectdam.ui.components.ordercomponents.OrderTabs
import kotlinx.coroutines.launch
import android.content.res.Configuration
import androidx.compose.ui.platform.LocalConfiguration
import com.example.restaurantproyectdam.data.controller.OrderViewModel


@Composable
fun OrdersScreen(navController: NavController,

                 ) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            if (isLandscape) {
                LandscapeContent(navController)
            } else {
                PortraitContent(navController)
            }
        }
    }
}

@Composable
private fun PortraitContent(navController: NavController) {
    Content(navController)
}

@Composable
private fun LandscapeContent(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            Header("ORDERS")
            Spacer(modifier = Modifier.height(8.dp))
            OrderTabsView(navController)
        }
    }
}

@Composable
private fun OrderTabsView(navController: NavController) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { OrderTabs.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex.value,
            modifier = Modifier.fillMaxWidth()
        ) {
            OrderTabs.entries.forEachIndexed { index, currentTab ->
                Tab(
                    selected = selectedTabIndex.value == index,
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                    text = { Text(text = currentTab.text) },
                    icon = {
                        Icon(
                            imageVector = if (selectedTabIndex.value == index) currentTab.selectedIcon else currentTab.unselectedIcon,
                            contentDescription = null
                        )
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.Top
        ) { page ->
            when (page) {
                //pending
                0 -> {
                    val orderProductsArray = arrayOf(
                        OrderProduct(
                            amount = 1,
                            orderProduct = ProductModel(
                                1,
                                "Tempura",
                                "Tempura is a Japanese dish...",
                                200.00f,
                                painterResource(R.drawable.tempura),
                                1
                            )
                        ),
                        OrderProduct(
                            amount = 2,
                            orderProduct = ProductModel(
                                2,
                                "Dango",
                                "Dango is a Japanese dessert...",
                                100.00f,
                                painterResource(R.drawable.dango),
                                2
                            )
                        )
                    )
                    OrderCard(id = 1, status = false, products = orderProductsArray, navController = navController)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { navController.navigate("") }) {
                        Text("Go to Details")
                    }
                }
                //ready
                1, 2 -> {
                    Text(
                        text = "There are no orders",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
@SuppressLint("Range")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun Content(navController: NavController) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { OrderTabs.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp) // Responsive padding
    ) {
        Header("ORDERS")
        Spacer(modifier = Modifier.height(8.dp)) // Responsive space

        TabRow(
            selectedTabIndex = selectedTabIndex.value,
            modifier = Modifier.fillMaxWidth()
        ) {
            OrderTabs.entries.forEachIndexed { index, currentTab ->
                Tab(
                    selected = selectedTabIndex.value == index,
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                    text = { Text(text = currentTab.text) },
                    icon = {
                        Icon(
                            imageVector = if (selectedTabIndex.value == index) currentTab.selectedIcon else currentTab.unselectedIcon,
                            contentDescription = null
                        )
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.Top
        ) { page ->
            when (page) {
                0 -> {
                    val orderProductsArray = arrayOf(
                        OrderProduct(
                            amount = 1,
                            orderProduct = ProductModel(
                                1,
                                "Tempura",
                                "Tempura is a Japanese dish...",
                                200.00f,
                                painterResource(R.drawable.tempura),
                                1
                            )
                        ),
                        OrderProduct(
                            amount = 2,
                            orderProduct = ProductModel(
                                2,
                                "Dango",
                                "Dango is a Japanese dessert...",
                                100.00f,
                                painterResource(R.drawable.dango),
                                2
                            )
                        )
                    )
                    OrderCard(id = 1, status = false, products = orderProductsArray, navController = navController)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { navController.navigate("") }) {
                        Text("Go to Details")
                    }
                }
                1 -> {
                    Text(
                        text = "There are no orders",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                2 -> {
                    Text(
                        text = "There are no orders",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OrdersScreenPreview() {
    OrdersScreen(navController = rememberNavController())
}
