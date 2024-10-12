package com.example.restaurantproyectdam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.restaurantproyectdam.ui.screens.AdminOrdersScreen
import com.example.restaurantproyectdam.ui.screens.AdminProductsScreen
import com.example.restaurantproyectdam.ui.screens.CategoryProductsScreen
import com.example.restaurantproyectdam.ui.screens.HomeScreen
import com.example.restaurantproyectdam.ui.screens.LoginScreen
import com.example.restaurantproyectdam.ui.screens.MenuScreen
import com.example.restaurantproyectdam.ui.screens.OrdersScreen
import com.example.restaurantproyectdam.ui.screens.ProfileScreen
import com.example.restaurantproyectdam.ui.screens.RegisterScreen
import com.example.restaurantproyectdam.ui.screens.SingleProductScreen
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
    //Scaffold (
        //color = Color.White
    //    bottomBar={ BottomBar(navController = navController) },
    //    floatingActionButton = { SearchButton(onClick = {}) }
    //) { innerPadding->
    //    Column(
    //       modifier = Modifier.padding(innerPadding)
    //    ){
            SetupNavGraph(navController = navController)
    //    }
    //}
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
        //composable("menu") { CategoriesScreen(navController) }
        composable("menu") { MenuScreen(navController) }
        //Ruta con parametro para CategoryProducts
        composable("categoryProducts/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            id?.let {
                CategoryProductsScreen(navController, it)
            }
        }
        composable("singleProduct/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            id?.let {
                SingleProductScreen(navController, it)
            }
        }


    }
}