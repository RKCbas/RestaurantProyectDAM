package com.example.restaurantproyectdam.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.ui.navigation.BottomBarOpt



// Bottom Navigation Bar
@Composable
fun BottomBar(navController: NavController){
    val screens = listOf( //Sealed Classes
        BottomBarOpt.Menu,
        BottomBarOpt.Home,
        BottomBarOpt.Orders,
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
    navController: NavController // To navigate to other destinations
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


// Header
@Composable
fun Header(text : String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(MaterialTheme.colorScheme.secondaryContainer),
        contentAlignment = Alignment.Center,
    ){
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.logo_fuji),
            contentDescription = "Custom SVG Icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(0.5f)
            ,
        )
        Text(text,
            style = MaterialTheme.typography.titleLarge,
            //fontSize = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

//Search bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Material3SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var active by remember { mutableStateOf(false) }

    SearchBar(
        query = query,
        onQueryChange = onQueryChanged,
        onSearch = {
            onSearch(it)
            active = false
        },
        active = active,
        onActiveChange = { active = it },
        placeholder = { Text("Search...") },
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChanged("") }) {
                    Icon(Icons.Filled.Clear, contentDescription = "Clear Search")
                }
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp, 2.dp, 10.dp, 10.dp)
    ) {
        // Optional: Provide additional suggestions or content when active
        Text("Search results or suggestions go here")
    }
}

@Preview(showBackground = true)
@Composable
fun Material3SearchBarPreview() {
    var query by remember { mutableStateOf("") }
    Material3SearchBar(
        query = query,
        onQueryChanged = { newQuery ->
            query = newQuery
        },
        onSearch = { searchQuery ->
            // Perform search logic
        }
    )
}