package com.example.restaurantproyectdam.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.ui.screens.myUserId

sealed class BottomBarOpt(
    val route: String,
    val title: String,
    val icon: Any
){
    /**
     * composable("home"){ HomeScreen(navController) }
     * composable("login"){ LoginScreen(navController) }
     * composable("register") { RegisterScreen(navController) }
     * composable("orders") { OrdersScreen(navController) }
     * composable("adminOrders") { AdminOrdersScreen(navController) }
     * composable("adminProducts") { AdminProductsScreen(navController) }
     */

    object Menu : BottomBarOpt(
        route ="menu",
        title = "Menu",
        icon = R.drawable.menu
    )
    object Orders : BottomBarOpt(
        route ="orders",
        title = "Orders",
        icon = R.drawable.bag
    )
    object Home : BottomBarOpt(
        route ="home/{$myUserId}",
        title = "Home",
        icon = Icons.Outlined.Home
    )
    object Profile : BottomBarOpt(
        route ="profile",
        title = "Profile",
        icon = Icons.Outlined.Person
    )
    object Cart: BottomBarOpt(
        route = "cart",
        title = "Cart",
        icon = Icons.Outlined.ShoppingCart
    )

}