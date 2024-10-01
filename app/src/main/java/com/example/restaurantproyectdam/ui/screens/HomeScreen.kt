package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.restaurantproyectdam.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (navController: NavController){
    Scaffold(
        topBar= {
            TopAppBar(
                /*colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),*/
                title = {
                    Text("Top app bar")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                /*containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary*/
            ){
                NavbarMenu(navController = navController)
            }
        }
    ){ //Main content
        innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ){
            LazyColumn(modifier=Modifier.fillMaxHeight(1f)){
                item {
                    Text(text="Categorías")
                }
                item{
                    LazyRow {
                        items(count = 10){
                            CategoryItem()
                        }
                    }
                }



            }
        }
    }

        //



}

@Preview(showBackground=true)
@Composable
fun CategoryItem(){
    Card( // Pattern that applies to all the 10 items
        modifier = Modifier
            .width(110.dp)
            .height(120.dp)
            .padding(10.dp, 5.dp, 5.dp, 0.dp)
            .clip(RoundedCornerShape(10.dp)) // Like border radius
            //.background(Color.White),
        //elevation = 6.dp // adds a shadow

    ){
        Column(
            modifier= Modifier
                .padding(5.dp),
            horizontalAlignment= Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(painter=painterResource(id = R.drawable.sushi),
                contentDescription = "sushi",
                //contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            //Spacer(modifier = Modifier.padding(5.dp)) //Leaves some space

            Text(
                text="Test",
                //color=Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun NavbarMenu(navController: NavController){
    LazyRow {
        item {
            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier.padding(5.dp)
            ) {
                Text(text = "Login")
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
                onClick = { navController.navigate("orders") },
                modifier = Modifier.padding(5.dp)
            ) {
                Text(text = "Orders", color = Color.White)
            }
        }
        item {
            Button(
                onClick = { navController.navigate("adminOrders") },
                modifier = Modifier.padding(5.dp)
            ) {
                Text(text = "Admin Orders")
            }
        }
        item {
            Button(
                onClick = { navController.navigate("adminProducts") },
                modifier = Modifier.padding(5.dp)
            ) {
                Text(text = "Admin Products")
            }
        }

    }
}