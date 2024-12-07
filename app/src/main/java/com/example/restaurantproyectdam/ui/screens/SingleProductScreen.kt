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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.data.model.createArrayCategories
import com.example.restaurantproyectdam.data.model.createArrayProducts
import com.example.restaurantproyectdam.ui.components.BottomBar
import com.example.restaurantproyectdam.ui.components.Header
import com.example.restaurantproyectdam.ui.components.SearchButton
import com.example.restaurantproyectdam.ui.components.homecomponents.ProductData
import kotlinx.coroutines.launch

var idProduct: Int ?=null


@Preview(showBackground = true)
@Composable
fun PreviewSingleProductScreen(){
    SingleProductScreen(navController = rememberNavController(), id = 1,1)
}

@Composable
fun SingleProductScreen (navController: NavController, id: Int, userId:Int?) {
    naveController = navController
    myUserId = userId
    idProduct = id
    Scaffold (
        //color = Color.White
        bottomBar={ BottomBar(navController = navController) },
        //floatingActionButton = { SearchButton(onClick = {}) }
    ) { innerPadding->
        Column(
            modifier = Modifier.padding(innerPadding)
                //.background(MaterialTheme.colorScheme.primaryContainer)
        )
        {
            content()
        }
    }
}

@Composable
fun content(){
    Header("")
    InfoCategory()
    SendSingleId()
    ButtonsProduct()
}


@Composable
fun SendSingleId() {
    if (idProduct != null) {
        val arrayProducts = createArrayProducts()

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(26.dp)

        ) {
            items(arrayProducts) { product ->
                if (product.id == idProduct) {
                    InfoProduct(
                        product.id, product.title, product.description,
                        product.cost.toString(), product.image, product.category
                    )
                }
            }
        }
    }else{
        Text("Page 505")
    }
}

@Composable
fun InfoProduct(id:Int, title:String, description:String, cost:String, image:Painter, category:Int){
    Column (modifier = Modifier
        .fillMaxSize())
    {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
        )
        {
            Image(
                painter = image,
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(180.dp)
                    .height(170.dp)
            )
        }
        Column (){
            Text(title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(20.dp,6.dp,20.dp,7.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                ,contentAlignment = Alignment.TopCenter){
                Text("$" + cost.toString(),
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
@Preview(showBackground = true)
@Composable
fun ButtonsProduct(){
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
fun InfoCategory() {
    val arrayProductss = createArrayProducts()
    val arrayCategoriess = createArrayCategories()

    for (product in arrayProductss ){
        if (product.id == idProduct) {
            for(category in arrayCategoriess){
                if(category.id == product.category){
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