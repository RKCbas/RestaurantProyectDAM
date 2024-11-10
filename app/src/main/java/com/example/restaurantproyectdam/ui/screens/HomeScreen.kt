package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.data.model.CategoryModel
import com.example.restaurantproyectdam.data.model.createArrayCategories
import com.example.restaurantproyectdam.ui.components.BottomBar
import com.example.restaurantproyectdam.ui.components.Material3SearchBar
import com.example.restaurantproyectdam.ui.components.SearchButton
import com.example.restaurantproyectdam.ui.components.homecomponents.PagerScreen


//@OptIn(ExperimentalMaterial3Api::class)

var myNavController: NavController ?= null;

@Composable
fun HomeScreen (navController: NavController){

    myNavController = navController


    Scaffold (
        //color = Color.White
        bottomBar={ BottomBar(navController = navController) },
        //floatingActionButton = { SearchButton(onClick = {}) }
    ) { innerPadding->
        Column(
            modifier = Modifier.padding(innerPadding)
        ){
            Content()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Content(){
    val categories =createArrayCategories()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)

        //.padding(25.dp)
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Icon(
                painter=painterResource(R.drawable.logo_fuji),
                contentDescription ="logo",
                modifier= Modifier
                    //.size(150.dp)
                    .width(170.dp)
                    .height(100.dp),
                tint = MaterialTheme.colorScheme.inversePrimary // Set the color you want here
            )
            Text("JAPANESE",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.inversePrimary,
                //fontSize = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text("Restaurant",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.inversePrimary,
            )
            var query by remember { mutableStateOf("") }
            Material3SearchBar(
                modifier = Modifier.padding(top = 0.dp),
                query = query,
                onQueryChanged = { newQuery ->
                    query = newQuery
                },
                onSearch = { searchQuery ->
                    // Perform search logic
                }
            )
        }
        LazyColumn(modifier=Modifier.fillMaxHeight(1f)){
            item {
                Text(text="Categories",
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.titleMedium)
            }
            item{
                LazyRow {

                    items(categories.size){
                        index ->
                        CategoryItem(categories[index])
                    }
                }
            }
            item{
                Column(
                    modifier = Modifier.padding(20.dp)
                ){
                    Text(text="Suggestions",
                        modifier = Modifier.padding(10.dp),
                        style = MaterialTheme.typography.titleMedium)
                    myNavController?.let { PagerScreen(it) }
                }

            }
            item{
                Column(
                    modifier = Modifier.padding(20.dp)
                ){
                    Text(text="Favorites",
                        modifier = Modifier.padding(10.dp),
                        style = MaterialTheme.typography.titleMedium)
                    myNavController?.let { PagerScreen(it) }
                }
            }
            item{
                Column(
                    modifier = Modifier.padding(20.dp)
                ){
                    Text(text="Most Popular",
                        modifier = Modifier.padding(10.dp),
                        style = MaterialTheme.typography.titleMedium)
                    myNavController?.let { PagerScreen(it) }
                }

            }


        }
    }
}

//@Preview(showBackground=true)
@Composable
private fun CategoryItem(category: CategoryModel){
    Button(
        // Pattern that applies to all the 10 items
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .padding(10.dp, 5.dp, 5.dp, 0.dp)
            .clip(RoundedCornerShape(10.dp)), // Like border radius
        //.background(color = MaterialTheme.colorScheme.surface),
        //.background(Color.White),
        //elevation = 6.dp // adds a shadow
        onClick = {
            myNavController?.navigate("categoryProducts/${category.id}")
        },
        //MaterialTheme.colorScheme.surface
        //ButtonDefaults.buttonColors(Primary)
        //colors = MaterialTheme.colorScheme.surface,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp), // Mimicking Card elevation
        shape = MaterialTheme.shapes.medium, // Use the shape of a Card
        //contentPadding = PaddingValues(16.dp) // You can adjust padding to match a Card's style
    ){
        Column(
            modifier= Modifier
                .padding(5.dp),
            horizontalAlignment= Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(painter= category.image,
            //painterResource(id = R.drawable.sushi),
                contentDescription = category.name,
                //contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            //Spacer(modifier = Modifier.padding(5.dp)) //Leaves some space

            Text(
                text=category.name,
                modifier=Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                //color=Color.Black,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview(){
    HomeScreen(navController = rememberNavController())
}