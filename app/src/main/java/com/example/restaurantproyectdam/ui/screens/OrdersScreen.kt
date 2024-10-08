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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.ui.components.ordercomponents.OrderTabs
import kotlinx.coroutines.launch

@SuppressLint("Range")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreen (navController: NavController){
    //VARIABLES
    //Trigger suppend function that will animate the scroll inside the horizontal pager
    val scope = rememberCoroutineScope()

    //pager states should passed directly to the horizontal pager composable to control its behavior
    val pagerState = rememberPagerState(pageCount = { OrderTabs.entries.size})

    //A selected tab index should be used to control which tab is currently selected
    //Also which data to show inside the horizontal pager as well
    val selectedTabIndex = remember{
        derivedStateOf {  //So we can avoid unnecessary recompositions
            // and improve performance in responsiveness in our app
            pagerState.currentPage
        }
    }

        /*LazyRow {
            item {
                Button(
                    onClick = { navController.navigate("home") },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(text = "Home", color = Color.White)
                }
            }
            item {
                Button(
                    onClick = { navController.navigate("login") },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(text = "Login", color = Color.White)
                }
            }
            item {
                Button(
                    onClick = { navController.navigate("register") },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(text = "Register", color = Color.White)
                }
            }
            item {
                Button(
                    onClick = { navController.navigate("adminOrders") },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(text = "Admin Orders", color = Color.White)
                }
            }
            item {
                Button(
                    onClick = { navController.navigate("adminProducts") },
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(text = "Admin Products", color = Color.White)
                }
            }

        }*/
        Column(
            modifier = Modifier
                .fillMaxSize()
                //.padding(top = 100.dp) // to leave the space for the top bar
        ){
            Header()
            TabRow(
                selectedTabIndex = selectedTabIndex.value,
                modifier = Modifier.fillMaxWidth()
            ) {
                //Loop through all enum entries
                OrderTabs.entries.forEachIndexed{ index,currentTab->
                    Tab(
                        selected = selectedTabIndex.value == index, // Check if this tab is selected
                        selectedContentColor = MaterialTheme.colorScheme.primary,
                        unselectedContentColor = MaterialTheme.colorScheme.outline,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(currentTab.ordinal)
                            }
                        },
                        text = { Text(text = currentTab.text)},
                        icon = {
                            Icon(
                                imageVector = if(selectedTabIndex.value == index)
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
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(text = OrderTabs.entries[selectedTabIndex.value].text)
                }
            }
        }

}

@Preview(showBackground = true)
@Composable
fun Header(){
    Box(
        modifier =Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(MaterialTheme.colorScheme.secondaryContainer),
        contentAlignment = Alignment.Center,
    ){
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.logo_fuji),
            contentDescription = "Custom SVG Icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
                .alpha(0.5f)
            ,
        )
        Text("ORDERS",
            style = MaterialTheme.typography.titleLarge,
            //fontSize = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }
}