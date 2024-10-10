package com.example.restaurantproyectdam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.restaurantproyectdam.ui.components.BottomBar
import com.example.restaurantproyectdam.ui.components.SearchButton
import com.example.restaurantproyectdam.ui.navigation.BottomBarOpt
import com.example.restaurantproyectdam.ui.screens.AdminOrdersScreen
import com.example.restaurantproyectdam.ui.screens.AdminProductsScreen
import com.example.restaurantproyectdam.ui.screens.CategoriesScreen
import com.example.restaurantproyectdam.ui.screens.HomeScreen
import com.example.restaurantproyectdam.ui.screens.LoginScreen
import com.example.restaurantproyectdam.ui.screens.MenuScreen
import com.example.restaurantproyectdam.ui.screens.OrdersScreen
import com.example.restaurantproyectdam.ui.screens.ProfileScreen
import com.example.restaurantproyectdam.ui.screens.RegisterScreen
import com.example.restaurantproyectdam.ui.theme.RestaurantProyectDAMTheme


//This is development

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RestaurantProyectDAMTheme{
                ComposeMultiScreenApp()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ComposeMultiScreenApp(){ // MAIN CONTENT
    val navController = rememberNavController()
    Scaffold (
        //color = Color.White
        bottomBar={ BottomBar(navController = navController) },
        floatingActionButton = { SearchButton(onClick = {}) }
    ) { innerPadding->
        Column(
           modifier = Modifier.padding(innerPadding)
        ){
            SetupNavGraph(navController = navController)
        }
    }
}

@Composable
fun SetupNavGraph (navController: NavHostController){
    NavHost(navController = navController, startDestination = "home"){
        composable("home"){ HomeScreen(navController) }
        composable("login"){ LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("orders") { OrdersScreen(navController) }
        composable("adminOrders") { AdminOrdersScreen(navController) }
        composable("adminProducts") { AdminProductsScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("menu") { CategoriesScreen(navController) }

    }
}