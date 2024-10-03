package com.example.restaurantproyectdam.ui.components.homecomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.restaurantproyectdam.R

@Composable
fun SinglePage(page: ProductData){
    Card(
        modifier = Modifier.clip(RoundedCornerShape(30.dp))
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                //horizontal
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = page.image),
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
                Text(text=page.title,
                    style= MaterialTheme.typography.titleLarge,
                    //fontFamily= FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    //fontSize = 28.sp
                )
                Spacer(modifier = Modifier.size((12.dp)))
                Text(
                    text = page.description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.size((12.dp)))
                Text(
                    text = "$"+page.cost.toString()+"MXN",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium
                )


            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun SinglePagePreview(){
    val page : ProductData = ProductData("Title","blabalbalalbalblablbalbbllbalalalbalb",100.0f, R.drawable.sushi)
    SinglePage(page = page)
}