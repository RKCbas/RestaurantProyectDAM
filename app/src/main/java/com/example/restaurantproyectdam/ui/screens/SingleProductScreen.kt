package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.restaurantproyectdam.R

@Composable
fun SingleProductScreen(navController: NavController){
    Column (modifier = Modifier
        .fillMaxSize()) {
        Headers()
        ProductData()
        ButtonsProduct()
    }
}

@Composable
fun ProductData(){
    Column (modifier = Modifier
        .fillMaxSize())
    {
        Box(modifier = Modifier.
            padding(10.dp)
            .align(Alignment.CenterHorizontally))
        {
            Image(painter = painterResource(R.drawable.sushi),
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(180.dp)
                    .height(170.dp)
                    )
        }
        Column (){
            Text("Product name",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(20.dp,6.dp,20.dp,7.dp))
            Box(modifier = Modifier.fillMaxWidth()
                .padding(top = 8.dp)
                ,contentAlignment = Alignment.TopCenter){
                Text("$999",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp)
            }
            Column {
                Text("Descripción",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp,
                    modifier = Modifier.padding(20.dp,2.dp))
                Text("Descripcion Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.",
                    maxLines = 6,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(20.dp,2.dp,20.dp,7.dp),
                    fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun Headers(){
    Box(
        modifier =Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(MaterialTheme.colorScheme.secondaryContainer),
        contentAlignment = Alignment.Center,
    ){
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.logo_fuji),
            contentDescription = "Custom SVG Icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
                .alpha(0.5f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonsProduct(){
    Row(horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
        .fillMaxSize()
            .padding(top = 20.dp))
    {

        OutlinedButton(onClick = {}) {
            Icon(Icons.Outlined.ShoppingCart, contentDescription = "Cart Icon",
               modifier = Modifier.size(AssistChipDefaults.IconSize),
                tint = Color.Black)
            Text("Agregar al Carrito", modifier = Modifier
                .padding(start = 7.dp), color = Color(0xFFFFA59A)
            )
        }
        OutlinedButton(onClick = {}) {
            Icon(Icons.Outlined.CheckCircle, contentDescription = "Shop Icon",
                modifier = Modifier.size(AssistChipDefaults.IconSize),
                tint = Color.Black)
            Text("Ordenar ahora", modifier = Modifier
                .padding(start = 10.dp), color = Color(0xFFFFA59A)
            )
        }
    }
}