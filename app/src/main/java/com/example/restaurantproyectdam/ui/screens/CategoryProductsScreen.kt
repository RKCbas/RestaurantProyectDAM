package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowLeft
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.restaurantproyectdam.data.model.ProductModel
import com.example.restaurantproyectdam.data.model.createArrayCategories
import com.example.restaurantproyectdam.data.model.createArrayProducts
import com.example.restaurantproyectdam.ui.components.BottomBar
import com.example.restaurantproyectdam.ui.components.SearchButton

var idd: Int ?=null

@Composable
fun CategoryProductsScreen (navController: NavController, id: Int) {
    naveController = navController
    idd = id
    Scaffold (
        //color = Color.White
        bottomBar={ BottomBar(navController = navController) },
        floatingActionButton = { SearchButton(onClick = {}) }
    ) { innerPadding->
        Column(
            modifier = Modifier.padding(innerPadding)
        )
        {
            Contents()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Contents(){
    Column(modifier = Modifier.fillMaxSize()
        .background(Color.LightGray)) {
        searchBar("menu")
        CategoriesInfo()
        Products()
    }
}

@Composable
fun Products(){
if (idd!=null){
    val arrayProducts = createArrayProducts()

    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .padding(26.dp)

    ){
        items(arrayProducts){product ->
            if(product.category == idd){
                ProductDataa(product.id, product.title, product.description,
                    product.cost, product.image, product.category)
            }
        }
    }
}else{

}
}

@Composable
fun ProductDataa(id:Int, title:String, description:String, cost:Float, image:Painter, category:Int){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Column (modifier = Modifier
            .weight(2f)
            .padding(end = 10.dp)) {
            Text(title, style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold)
            Text(description, style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(top = 10.dp))
            Text(cost.toString(), style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp))
        }
        Image(painter = image,
            contentDescription = "$title image",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(90.dp)
                .weight(1f)
                .padding(top = 20.dp)
        )

    }
}

@Composable
fun CategoriesInfo(){
    val arrayCategories = createArrayCategories()
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(26.dp)
    ){
        items(arrayCategories){category ->
            CategorySelected(category.id,category.name)
        }
    }
}

@Composable
fun CategorySelected(id:Int, name:String){
    Row (modifier = Modifier.fillMaxWidth()
        .padding(start = 14.dp)
        , horizontalArrangement = Arrangement.SpaceEvenly){
        Text(
            text = name,
            fontWeight = if (idd == id) FontWeight.Bold else FontWeight.Normal,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .clickable {
                    naveController?.navigate("categoryProducts/$id")
                }
        )

    }
}