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
import com.example.restaurantproyectdam.ui.navigation.BottomBarOpt
import com.example.restaurantproyectdam.ui.screens.AdminOrdersScreen
import com.example.restaurantproyectdam.ui.screens.AdminProductsScreen
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RestaurantProyectDAMTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeMultiScreenApp(){

    val navController = rememberNavController()
    Scaffold (
        //color = Color.White
        bottomBar={ BottomBar(navController = navController) }
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
        composable("menu") { MenuScreen(navController) }

    }
}


@Composable
fun BottomBar(navController:NavHostController){
    val screens = listOf( //Sealed Classes
        BottomBarOpt.Menu,
        BottomBarOpt.Orders,
        BottomBarOpt.Home,
        BottomBarOpt.Profile
    )
    //Whenever its value is changed, we will be notified about that
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestionation = navBackStackEntry?.destination // Nullable type // current location
    //Predefined composable function to add items to the nav bar
    NavigationBar(){
        screens.forEach{screen ->
            AddItem(screen = screen, currentDestination = currentDestionation, navController = navController)
        }
    }
}

@Composable
fun RowScope.AddItem(// Extends from RowScope
    screen:BottomBarOpt, //Sealed class
    currentDestination: NavDestination?, //Nullable type // The current location
    navController: NavHostController // To navigate to other destinations
){
    /*BottomNavigationItem(
        onClick = { /*TODO*/ },
        icon = { /*TODO*/ },
        label = {Text(text = screen.title)}
    )*/
    NavigationBarItem(
        onClick = {
            navController.navigate(screen.route){
                //Avoid back stack problem
                // PopUp the new screen instead of creating a new intace every time
                popUpTo(navController.graph.findStartDestination().id)
                //popUpTo(navController.navigate(BottomBarScreen.HomeBB.route))
                launchSingleTop=true
                /**
                 * This allows us to avoid multiple copies of of the same destination
                 * when reselecting the same item
                 */
            }
        },
        icon = {
            when (val icon = screen.icon) {
                is ImageVector -> Icon(imageVector = icon, contentDescription = "icon")
                is Int -> Icon(painterResource(icon), contentDescription = "icon", modifier = Modifier.size(30.dp))
            }
               },
        label = {Text(text = screen.title)}, //Text
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.route // Checks if is selected
        } == true
    )
}
