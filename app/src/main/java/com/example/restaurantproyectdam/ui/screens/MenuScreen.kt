package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowLeft
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.data.model.CategoryModel
import com.example.restaurantproyectdam.data.model.createArrayCategories
import com.example.restaurantproyectdam.ui.components.BottomBar
import com.example.restaurantproyectdam.ui.components.SearchButton

var naveController: NavController ?=null

@Composable
fun MenuScreen (navController: NavController) {
    naveController = navController
    Scaffold (
        //color = Color.White
        bottomBar={ BottomBar(navController = navController) },
        floatingActionButton = { SearchButton(onClick = {}) }
    ) { innerPadding->
        Column(
            modifier = Modifier.padding(innerPadding)
        ){
            Content()
        }
    }
}

@Composable
private fun Content(){ //CÓDIGO DE YAHAIRA
        val arrayCategories = createArrayCategories()
        Column(modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.secondaryContainer)) {
            searchBar("home")
            CategoriesGrid(arrayCategories)
        }
}

@Composable
fun searchBar(route:String){
    Row (modifier = Modifier.statusBarsPadding().fillMaxWidth().padding(12.dp,0.dp)){
        Icon(
            Icons.Sharp.KeyboardArrowLeft,
            contentDescription = "Arrow back Icon",
            modifier = Modifier
                .padding(7.dp)
                .clickable {
                    naveController?.navigate(route)
                }
        )
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))
        {
            Icon (
                Icons.Sharp.Search,
                contentDescription = "Search Icon",
                modifier = Modifier
                    .padding(4.dp)
            )
        }
    }

}

@Composable
fun CategoriesGrid(arrayCategories: Array<CategoryModel>){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(26.dp)
    ){
        items(arrayCategories){category ->
            CategoryCard(category.id, category.name, category.image)
        }
    }
}



@Composable
fun CategoryCard(id:Int, name:String, image: Painter){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable {
                naveController?.navigate("categoryProducts/$id")
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Image(
            painter = image,
            contentDescription = "Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(10.dp)
        )
        Text(
            text=name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(10.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}