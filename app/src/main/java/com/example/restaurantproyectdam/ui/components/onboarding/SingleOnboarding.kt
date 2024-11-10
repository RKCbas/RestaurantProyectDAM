package com.example.restaurantproyectdam.ui.components.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.data.model.ProductModel
import com.example.restaurantproyectdam.ui.screens.naveController

@Composable
fun SingleOnboarding(page: OnboardingData,navController: NavController){
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
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
                painter = painterResource(id =page.image ),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(300.dp)
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Text(text=page.title,
                    style= MaterialTheme.typography.titleLarge,
                    //fontFamily= FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = 60.sp,
                    color = Color.White,
                    //fontSize = 28.sp
                )
                Spacer(modifier = Modifier.size((12.dp)))
                Text(
                    text = page.description,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 30.sp,
                    lineHeight = 40.sp,  // Set your desired line spacing here
                    textAlign = TextAlign.Center,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.size((70.dp)))
                Button(
                    onClick = { navController.navigate("login")},
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.errorContainer)
                ) {
                    Text(
                        text = "GET STARTED",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color.White
                    )
                }

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun SingleOnBoardingPreview(){
    val page : OnboardingData = OnboardingData("Title","blabalbalalbalblablbalbbllbalalalbalb", R.drawable.sushi)
    SingleOnboarding(page = page, rememberNavController())
}