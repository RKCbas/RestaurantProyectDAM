package com.example.restaurantproyectdam.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.restaurantproyectdam.ui.navigation.BottomBarOpt


@Composable
fun BottomBar(navController: NavHostController){
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
    screen: BottomBarOpt, //Sealed class
    currentDestination: NavDestination?, //Nullable type // The current location
    navController: NavHostController // To navigate to other destinations
){
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
        label = { Text(text = screen.title) }, //Text
        selected = currentDestination?.hierarchy?.any{
            it.route == screen.route // Checks if is selected
        } == true
    )
}

@Composable
fun SearchButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
    ) {
        Icon(Icons.Filled.Search, "Floating action button.")
    }
}