package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.restaurantproyectdam.data.model.createArrayProducts
import com.example.restaurantproyectdam.ui.components.BottomBar
import com.example.restaurantproyectdam.ui.components.Header
import com.example.restaurantproyectdam.ui.components.SearchButton

var idp: Int ?=null
var totall: Float ?=null


@Composable
fun PaymentScreen (navController: NavController, id: Int) {
    naveController = navController
    idp = id
    Scaffold (
        //color = Color.White
        bottomBar={ BottomBar(navController = navController) },

    ) { innerPadding->
        Column(
            modifier = Modifier.padding(innerPadding)
        )
        {
            PaymentContent(navController)
        }
    }
}

@Composable
fun PaymentContent(navController: NavController){
    Column(modifier = Modifier.fillMaxSize()) {
        Header("Payment")
        Total()
        ButtonsPayment(navController)
        ProductInfoo()
    }
}

@Composable
fun Total(){
    var total by remember { mutableFloatStateOf(totall?:0f) }
    Column(modifier = Modifier.fillMaxWidth()
        .padding(20.dp)){
        Text("Total",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Light)
        Text("$ $total MXN",
            modifier = Modifier.padding(top = 6.dp),
            style = MaterialTheme.typography.titleLarge
        )
        Row(modifier = Modifier.fillMaxWidth()
            .padding(top = 6.dp)) {
            Icon(Icons.Outlined.Place, contentDescription = "Icon place")
            total += 130
            Text("Shipment cost $ $total MXN",
                modifier = Modifier.padding(start = 10.dp, top = 3.dp))
        }
    }
}

@Composable
fun ButtonsPayment(navController: NavController){

    var showAlertDialog by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }
    if(showAlertDialog){
        AlertDialog(
            icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "")},
            title = {Text("Order now?")},
            text = {Text("Are you sure you want to buy this item?")},
            onDismissRequest = {},
            confirmButton = {
                TextButton(onClick = {
                    selectedOption = "Confirm"
                    showAlertDialog=false
                    navController.navigate("orders")
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    selectedOption = "Dismiss"
                    showAlertDialog=false
                }) {
                    Text(text = "Dismiss")
                }
            }
        )
    }

    var text by remember { mutableStateOf("") }
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Promotion Code") },
                singleLine = true,
                modifier = Modifier
                    .width(250.dp)
                    .height(56.dp)
                    .align(Alignment.CenterVertically)
            )
            Button(
                onClick = {},
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .width(250.dp)
                    .height(56.dp)
                    .align(Alignment.CenterVertically)
            ){
                Text("Send")
            }
        }
        Button(
            onClick = {showAlertDialog=true},
            shape = RoundedCornerShape(9.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.CenterHorizontally)
                .padding(2.dp)
        ){
            Text("Pay")
        }
    }
}

@Composable
fun ProductInfoo(){
    if (idp!=null){
        val arrayProducts = createArrayProducts()

        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .padding(26.dp)

        ){
            items(arrayProducts){product ->
                if(product.id == idp){
                    ProductsList(product.id, product.title, product.description,
                        product.cost, product.image, product.category)
                }
            }
        }
    }else{

    }
}

@Composable
fun ProductsList(id:Int, title:String, description:String, cost:Float, image: Painter, category:Int){
    val tot by remember { mutableFloatStateOf(cost) }
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Image(painter = image,
            contentDescription = "$title image",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(90.dp)
                .weight(1f)
                .padding(top = 20.dp, end = 25.dp)
        )
        Column (modifier = Modifier
            .weight(2f)
            .padding(end = 10.dp)) {
            Text(title, style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold)
            Text(cost.toString(), style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 30.dp))
        }
        QuantityProduct(tot)
    }
}


@Composable
fun QuantityProduct(tot:Float){
    var quantityy by remember { mutableIntStateOf(1) }
    Row(modifier = Modifier.padding(6.dp,30.dp)) {
      Icon(Icons.Filled.Close, contentDescription = "Reducir cantidad",
          modifier = Modifier.padding(end = 13.dp)
              .clickable {
                  if(quantityy>1){
                  quantityy += -1
                  }
              })
        Text(quantityy.toString(), style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold)
        Icon(Icons.Sharp.Add, contentDescription = "Aumentar cantidad",
            modifier = Modifier.padding(start = 13.dp)
                .clickable {
                    quantityy++
                })
    }
    CalculateTotal(tot,quantityy)
}

@Composable
fun CalculateTotal(total:Float,quantity:Int){
    var ress by remember { mutableFloatStateOf(0f) }
    ress = (total*quantity).toFloat()
    Text("$"+ress.toString()+"MXN")
    totall=ress
}