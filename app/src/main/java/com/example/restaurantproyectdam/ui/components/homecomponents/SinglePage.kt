package com.example.restaurantproyectdam.ui.components.homecomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.CardDefaults.defaultCardColors
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.data.model.DishEntity
import com.example.restaurantproyectdam.data.model.DishModel
import com.example.restaurantproyectdam.data.model.ProductModel
import com.example.restaurantproyectdam.ui.screens.naveController

@Composable
fun SinglePage(page: DishEntity, navController: NavController, userId:Int?){

    Button(
        modifier = Modifier.clip(RoundedCornerShape(30.dp)),
        onClick={
            navController.navigate("singleProduct/${page.id}")

        },
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp), // Mimicking Card elevation
        shape = MaterialTheme.shapes.medium, // Use the shape of a Card
        contentPadding = PaddingValues(16.dp) // You can adjust padding to match a Card's style
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                //horizontal
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            AsyncImage(
                model = page.dish_image,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(180.dp)
            )
            Divider(modifier = Modifier.padding(vertical = 12.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
            ){
                Text(text=page.name,
                    style= MaterialTheme.typography.titleLarge,
                    //fontFamily= FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    //fontSize = 28.sp
                )
                Spacer(modifier = Modifier.size((12.dp)))
                Text(
                    text = page.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
                Spacer(modifier = Modifier.size((12.dp)))
                Text(
                    text = "$"+page.price.toString()+"MXN",
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyMedium
                )


            }
        }
    }

}

/*@Preview(showBackground = true)
@Composable
fun SinglePagePreview(){
    val page : ProductData = ProductData("Title","blabalbalalbalblablbalbbllbalalalbalb",100.0f, R.drawable.sushi)
    SinglePage(page = page)
}*/