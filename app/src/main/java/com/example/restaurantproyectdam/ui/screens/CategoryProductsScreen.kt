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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowLeft
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.restaurantproyectdam.data.controller.CategoryViewModel
import com.example.restaurantproyectdam.data.controller.DishViewModel
import com.example.restaurantproyectdam.data.database.AppDatabase
import com.example.restaurantproyectdam.data.database.DatabaseProvider
import com.example.restaurantproyectdam.data.model.CategoryEntity
import com.example.restaurantproyectdam.data.model.DishEntity
import com.example.restaurantproyectdam.data.model.ProductModel
import com.example.restaurantproyectdam.data.model.createArrayCategories
import com.example.restaurantproyectdam.data.model.createArrayProducts
import com.example.restaurantproyectdam.ui.components.BottomBar
import com.example.restaurantproyectdam.ui.components.SearchButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

var idd: Int ?=null

@Composable
fun CategoryProductsScreen (navController: NavController, id: Int, categoryViewModel: CategoryViewModel = androidx.lifecycle.viewmodel.compose.viewModel(), dishViewModel: DishViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val db: AppDatabase = DatabaseProvider.getDatabase(LocalContext.current)
    val categoryDao = db.categoryDao()
    val dishDao = db.dishDao()

    var categories by remember { mutableStateOf<List<CategoryEntity>>(emptyList()) }
    var dishes by remember { mutableStateOf<List<DishEntity>>(emptyList()) }

    naveController = navController
    idd = id
    Scaffold (
        //color = Color.White
        bottomBar={ BottomBar(navController = navController) },
        //floatingActionButton = { SearchButton(onClick = {}) }
    ) { innerPadding->

        LaunchedEffect(Unit) {
            categories =  withContext(Dispatchers.IO) {
                categoryViewModel.getCategories(db)
                categoryDao.getCategories()
            }
            dishes =  withContext(Dispatchers.IO) {
                dishViewModel.getDishes(db)
                dishDao.getDishes()
            }
        }

        val listState = rememberLazyListState()

        Column(
            modifier = Modifier.padding(innerPadding)
        )
        {
            Contents(categories, dishes)
        }
    }
}

@Composable
fun Contents(categories: List<CategoryEntity>, dishes: List<DishEntity>){
    Column(modifier = Modifier.fillMaxSize()
        //.background(Color.LightGray)
    ) {
        searchBar("menu")
        CategoriesInfo(categories)
        Products(dishes)
    }
}

@Composable
fun Products(dishes: List<DishEntity>){
if (idd!=null){
    val arrayProducts = createArrayProducts()
    LazyColumn (
        modifier = Modifier
            .fillMaxWidth()
            .padding(26.dp)
    ){
        items(dishes){dish ->
            if(dish.category_id == idd){
                ProductDataa(dish.dish_id, dish.name, dish.description,
                    dish.price, dish.dish_image, dish.category_id)
            }
        }
    }
}else{

}
}

@Composable
fun ProductDataa(id:Int, name:String, description:String, price:Float, image:String, category_id:Int){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable {
            naveController?.navigate("singleProduct/$id")
        },
        horizontalArrangement = Arrangement.SpaceBetween) {
        Column (modifier = Modifier
            .weight(2f)
            .padding(end = 10.dp)) {
            Text(name, style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold)
            Text(description, style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(top = 10.dp))
            Text(price.toString(), style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp))
        }
        AsyncImage(model = image,
            contentDescription = "$name image",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(90.dp)
                .weight(1f)
                .padding(top = 20.dp)
        )

    }
}

@Composable
fun CategoriesInfo(categories: List<CategoryEntity>){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(26.dp)
    ){
        items(categories){category ->
            CategorySelected(category.category_id,category.name)
        }
    }
}

@Composable
fun CategorySelected(id:Int, name:String){
    Row (modifier = Modifier.fillMaxWidth()
        .padding(start = 14.dp)
        , horizontalArrangement = Arrangement.SpaceEvenly){
        Button(
            onClick = {naveController?.navigate("categoryProducts/$id")},
        ){
            Text(
                text = name,
                fontWeight = if (idd == id) FontWeight.Bold else FontWeight.Normal,
                style = MaterialTheme.typography.titleMedium
            )
        }

    }
}