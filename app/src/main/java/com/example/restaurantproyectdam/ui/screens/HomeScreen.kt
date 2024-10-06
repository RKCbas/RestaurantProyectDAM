package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.ui.components.homecomponents.PagerScreen


//@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun HomeScreen (navController: NavController){
        Column(
            modifier = Modifier.fillMaxSize()
                //.padding(25.dp)
        ){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Icon(
                    painter=painterResource(R.drawable.logo_fuji),
                    contentDescription ="logo",
                    modifier=Modifier
                            //.size(150.dp)
                        .width(170.dp)
                        .height(100.dp)
                    //tint = Color.Red // Set the color you want here
                )
                Text("JAPANESE",
                    style = MaterialTheme.typography.titleLarge,
                    //fontSize = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text("Restaurant", style = MaterialTheme.typography.titleSmall)
            }
            LazyColumn(modifier=Modifier.fillMaxHeight(1f)){
                item {
                    Text(text="Categories",
                        modifier = Modifier.padding(10.dp),
                        style = MaterialTheme.typography.titleMedium)
                }
                item{
                    LazyRow {
                        items(count = 10){
                            CategoryItem()
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
                        PagerScreen()
                    }

                }
                item{
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ){
                        Text(text="Favorites",
                            modifier = Modifier.padding(10.dp),
                            style = MaterialTheme.typography.titleMedium)
                        PagerScreen()
                    }
                }
                item{
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ){
                        Text(text="Most Popular",
                            modifier = Modifier.padding(10.dp),
                            style = MaterialTheme.typography.titleMedium)
                        PagerScreen()
                    }

                }


            }
        }


}

@Preview(showBackground=true)
@Composable
fun CategoryItem(){
    Button( // Pattern that applies to all the 10 items
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .padding(10.dp, 5.dp, 5.dp, 0.dp)
            .clip(RoundedCornerShape(10.dp)), // Like border radius
            //.background(Color.White),
        //elevation = 6.dp // adds a shadow
        onClick={},
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
            Image(painter=painterResource(id = R.drawable.sushi),
                contentDescription = "sushi",
                //contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            //Spacer(modifier = Modifier.padding(5.dp)) //Leaves some space

            Text(
                text="Test",
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
fun HomeScreenPreview(){
    HomeScreen(navController = rememberNavController())
}