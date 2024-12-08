package com.example.restaurantproyectdam.ui.screens

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.data.controller.CartViewModel
import com.example.restaurantproyectdam.data.controller.CategoryViewModel
import com.example.restaurantproyectdam.data.controller.DishViewModel
import com.example.restaurantproyectdam.data.controller.LoginViewModel
import com.example.restaurantproyectdam.data.controller.UserIdViewModel
import com.example.restaurantproyectdam.data.database.AppDatabase
import com.example.restaurantproyectdam.data.database.DatabaseProvider
import com.example.restaurantproyectdam.data.model.AddToCartModelRequest
import com.example.restaurantproyectdam.data.model.CategoryEntity
import com.example.restaurantproyectdam.data.model.DishEntity
import com.example.restaurantproyectdam.data.model.createArrayCategories
import com.example.restaurantproyectdam.data.model.createArrayProducts
import com.example.restaurantproyectdam.ui.components.BottomBar
import com.example.restaurantproyectdam.ui.components.Header
import com.example.restaurantproyectdam.ui.components.SearchButton
import com.example.restaurantproyectdam.ui.components.homecomponents.ProductData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

var idProduct: Int ?=null


/*@Preview(showBackground = true)
@Composable
fun PreviewSingleProductScreen(){
    SingleProductScreen(navController = rememberNavController(), id = 1)
}*/

@Composable
fun SingleProductScreen (navController: NavController,
                         id: Int,
                         userIdViewModel: UserIdViewModel,
                         categoryViewModel: CategoryViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
                         dishViewModel: DishViewModel = androidx.lifecycle.viewmodel.compose.viewModel()             
) {


    val db: AppDatabase = DatabaseProvider.getDatabase(LocalContext.current)
    val categoryDao = db.categoryDao()
    val dishDao = db.dishDao()

    var categories by remember { mutableStateOf<List<CategoryEntity>>(emptyList()) }

    var dishes by remember { mutableStateOf<List<DishEntity>>(emptyList())  }

    naveController = navController
    idProduct = id
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
                //.background(MaterialTheme.colorScheme.primaryContainer)
        )
        {
            content(categories,userIdViewModel.cartId,id, dishes)

        }
    }
}

@Composable
fun content(categories: List<CategoryEntity>, cart_id: Int?, product_id: Int, dishes: List<DishEntity>){
    Header("")
    InfoCategory(categories)
    SendSingleId()
    if (cart_id != null) {
        ButtonsProduct(cart_id,product_id)
    }
}


@Composable
fun SendSingleId(dishes: List<DishEntity>) {
    if (idProduct != null) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(26.dp)

        ) {
            items(dishes) { dish ->
                if (dish.dish_id == idProduct) {
                    InfoProduct(
                        dish.dish_id, dish.name, dish.description,
                        dish.price.toString(), dish.dish_image, dish.category_id
                    )
                }
            }
        }
    }else{
        Text("Page 505")
    }
}

@Composable
fun InfoProduct(dish_id:Int, name:String, description:String, price:String, dish_image:String, category_id:Int){
    Column (modifier = Modifier
        .fillMaxSize())
    {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
        )
        {
            AsyncImage(
                model = dish_image,
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(180.dp)
                    .height(170.dp)
            )
        }
        Column (){
            Text(name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(20.dp,6.dp,20.dp,7.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                ,contentAlignment = Alignment.TopCenter){
                Text("$" + price.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp)
            }
            Column {
                Text("Description",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp,
                    modifier = Modifier.padding(20.dp,2.dp))
                Text(description,
                    maxLines = 6,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(20.dp,2.dp,20.dp,7.dp),
                    fontSize = 14.sp)
            }
        }
    }
}

@SuppressLint("RememberReturnType")
//@Preview(showBackground = true)
@Composable
fun ButtonsProduct(cart_id: Int,
                   product_id: Int,
                   viewModel: CartViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
                   //onNewDish  = { viewModel.api.addToCart(email, password) }

){
    val snackState = remember{ SnackbarHostState() }
    val snackScope = rememberCoroutineScope()

    SnackbarHost(hostState = snackState, Modifier)

    fun launchSnackBar(){
        snackScope.launch { snackState.showSnackbar("Added to the cart") }
    }


    Row(horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp))
    {




        OutlinedButton(onClick = {
            //Aquí se agrega al carrito
            if (cart_id != null) {
                println("entra")
                viewModel.addToCart(
                    cart_id,
                    dish_id = product_id,
                    AddToCartModelRequest(1)
                )
            }

            launchSnackBar()
        },
            border = BorderStroke(2.dp,
                color = MaterialTheme.colorScheme.error
            )
        ) {
            Icon(Icons.Outlined.ShoppingCart, contentDescription = "Cart Icon",
               modifier = Modifier.size(AssistChipDefaults.IconSize),
                //tint = Color.Black
                tint = MaterialTheme.colorScheme.error
            )
            Text("Add to the cart", modifier = Modifier
                .padding(start = 7.dp),
                color = MaterialTheme.colorScheme.error
            )
        }
        OutlinedButton(onClick = {
            naveController?.navigate("payment/$idProduct")
        },
            border = BorderStroke(2.dp,color = MaterialTheme.colorScheme.error),
        ) {
            Icon(Icons.Outlined.CheckCircle, contentDescription = "Shop Icon",
                modifier = Modifier.size(AssistChipDefaults.IconSize),
                tint = MaterialTheme.colorScheme.error
                //tint = Color.Black
                )
            Text("Order now", modifier = Modifier
                .padding(start = 10.dp),
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun InfoCategory(categories: List<CategoryEntity>, dishes: List<DishEntity>) {
    for (dish in dishes ){
        if (dish.dish_id == idProduct) {
            for(category in categories){
                if(category.category_id == dish.category_id){
                    ShowCategory(category.name)
                }
            }
        }
    }
}

@Composable
fun ShowCategory(name:String){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 30.dp, end = 30.dp, start = 30.dp)
    ) {
            Icon(Icons.Filled.KeyboardArrowLeft,
                contentDescription = "Button Return",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable {
                        naveController?.navigate("categoryProducts/$idd")
                    }
            )
            Text(name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                //color = Color.DarkGray,
                modifier = Modifier.align(Alignment.CenterEnd))
    }
}