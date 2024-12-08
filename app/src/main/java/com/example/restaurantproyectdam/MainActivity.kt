package com.example.restaurantproyectdam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.restaurantproyectdam.data.controller.UserIdViewModel
import com.example.restaurantproyectdam.ui.components.maps.MapsSearchView
import com.example.restaurantproyectdam.ui.components.maps.SearchViewModel
import com.example.restaurantproyectdam.ui.screens.AddressesScreen
import com.example.restaurantproyectdam.ui.screens.AdminOrdersScreen
import com.example.restaurantproyectdam.ui.screens.AdminProductsScreen
import com.example.restaurantproyectdam.ui.screens.CartScreen
import com.example.restaurantproyectdam.ui.screens.CategoryProductsScreen
import com.example.restaurantproyectdam.ui.screens.ContactUsScreen
import com.example.restaurantproyectdam.ui.screens.CustomProfileScreen
import com.example.restaurantproyectdam.ui.screens.HomeScreen
import com.example.restaurantproyectdam.ui.screens.LoginScreen
import com.example.restaurantproyectdam.ui.screens.MenuScreen
import com.example.restaurantproyectdam.ui.screens.OrderScreen
import com.example.restaurantproyectdam.ui.screens.OrdersScreen
import com.example.restaurantproyectdam.ui.screens.PaymentMethodsScreen
import com.example.restaurantproyectdam.ui.screens.PaymentScreen
import com.example.restaurantproyectdam.ui.screens.ProfileScreen
import com.example.restaurantproyectdam.ui.screens.RegisterScreen
import com.example.restaurantproyectdam.ui.screens.SingleProductScreen
import com.example.restaurantproyectdam.ui.screens.WelcomeScreen
import com.example.restaurantproyectdam.ui.theme.RestaurantProyectDAMTheme


//This is development

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RestaurantProyectDAMTheme {
                val userIdViewModel: UserIdViewModel = viewModel()
                val viewModel: SearchViewModel by viewModels()

                ComposeMultiScreenApp(viewModel, userIdViewModel)

            }
        }
    }
}


//@Preview(showBackground = true)
@Composable
fun ComposeMultiScreenApp(
    viewModel: SearchViewModel,
    userIdViewModel: UserIdViewModel
) { // MAIN CONTENT
    val navController = rememberNavController()

    //Scaffold (
    //color = Color.White
    //    bottomBar={ BottomBar(navController = navController) },
    //    floatingActionButton = { SearchButton(onClick = {}) }
    //) { innerPadding->
    //    Column(
    //       modifier = Modifier.padding(innerPadding)
    //    ){
    RestaurantProyectDAMTheme {
        SetupNavGraph(navController = navController, viewModel, userIdViewModel)
    }
    //    }
    //}
}

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    viewModel: SearchViewModel,
    userIdViewModel: UserIdViewModel
) {
    NavHost(navController = navController, startDestination = "onboarding") {

        composable("onboarding") { WelcomeScreen(navController = navController) }
        composable("home") { HomeScreen(navController, userIdViewModel) }

        composable("login") { LoginScreen(navController, userIdViewModel) }
        composable("register") { RegisterScreen(navController, userIdViewModel) }



        composable("orders") { OrdersScreen(navController) }

        composable("order/{orderId}"){ backStackEntry ->
            val id = backStackEntry.arguments?.getString("orderId")?.toIntOrNull()
            id?.let { OrderScreen(navController, orderId = it) }
        }





        composable("adminOrders") { AdminOrdersScreen(navController) }



        composable("cart"){
            CartScreen(navController)
        }

        composable("adminProducts") { AdminProductsScreen(navController) }


        //composable("profile") { ProfileScreen(navController) }
        //composable("menu") { CategoriesScreen(navController) }

        composable("menu") {
            MenuScreen(navController)
        }

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
                SingleProductScreen(navController, it, userId)
            }
        }

        composable("payment/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            id?.let {
                PaymentScreen(navController, it)
            }
        }

//        composable("OrderScreen/{id}") {
//            val id = it.arguments?.getString("id")?.toIntOrNull()
//            id?.let {
//                OrderScreen(navController, id)
//            }
//        }
        //composable("OrderScreen") { OrderScreen(navController = navController) }


        //Profile
        composable("profile") { ProfileScreen(navController) }
        composable("addresses") { AddressesScreen(navController, viewModel) }
        composable("payment_methods") { PaymentMethodsScreen(navController) }
        //composable("orders") { OrdersScreen(navController) }
        composable("edit_profile") { CustomProfileScreen(navController = navController) }
        composable("contact_us") { ContactUsScreen(navController) }

        //maps
        composable("MapsSearchView/{lat}/{long}/{address}", arguments = listOf(
            navArgument("lat") { type = NavType.FloatType },
            navArgument("long") { type = NavType.FloatType },
            navArgument("address") { type = NavType.StringType }
        )) {
            // Obtención de los argumentos con valores predeterminados en caso de que falten
            val lat = it.arguments?.getFloat("lat") ?: 0.0
            val long = it.arguments?.getFloat("long") ?: 0.0
            val address = it.arguments?.getString("address") ?: ""
            MapsSearchView(lat.toDouble(), long.toDouble(), address, navController)
        }

    }
}