package com.example.restaurantproyectdam.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.restaurantproyectdam.ui.components.maps.SearchViewModel
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.sharp.KeyboardArrowLeft
import androidx.compose.ui.text.font.FontWeight
import com.example.restaurantproyectdam.ui.components.maps.MapsSearchView

//import kotlin.math.round
lateinit var MyViewModel: SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressesScreen(navController: NavController,viewModel: SearchViewModel) {
    MyViewModel = viewModel

    //val viewModel: SearchViewModel by viewModels()

    Scaffold(
        topBar = {
            Row(
                Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
                    .fillMaxWidth() .padding(top = 50.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    Icons.Sharp.KeyboardArrowLeft,
                    contentDescription = "Arrow back Icon",
                    modifier = Modifier
                        .padding(7.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                        .size(40.dp)
                )
                Text(text ="My Directions", fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineLarge)
                //TopAppBar(title = { Text("") })
            }
            
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Aquí puedes agregar el contenido de tu pantalla, por ejemplo, una lista de direcciones.
                Text("Your Directions")
                LazyColumn {
                    items(count = 3){
                        Direction( "Aguascalientes. Ags Mx",navController )
                    }
                    item{
                        Text("Add a new direction")
                        val textFieldValue = remember { mutableStateOf("") }
                        Column(){
                            OutlinedTextField(
                                value = textFieldValue.value,
                                onValueChange = { textFieldValue.value = it },
                                label = { "Direction" },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Button(onClick = {
                                MyViewModel.getLocation(textFieldValue.toString())
                                if(MyViewModel.show){
                                    navController.navigate("MapsSearchView/${MyViewModel.lat}/${MyViewModel.long}/${MyViewModel.address}")
                                }
                            }){
                                Text(text = "Add new location")
                            }
                        }

                    }
                    /*item{
                        MapsSearchView()
                    }*/
                }

            }
        },
        /*floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }*/
    )
}

@Composable
//@Preview(showBackground = true)
fun Direction(direction:String = "Aguascalientes. Ags Mx",navController: NavController){

    val isTextFieldEnabled = remember{ mutableStateOf(false) }

    // State for the TextField value
    val textFieldValue = remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.SpaceBetween

    ){
        /*Text(
            direction,
            modifier = Modifier.padding(10.dp)
        )*/
        OutlinedTextField(
            value = textFieldValue.value,
            onValueChange = { textFieldValue.value = it },
            label = { Text(direction) },
            placeholder = {Text(direction)},
            enabled = isTextFieldEnabled.value, // Enable/Disable based on state
            modifier = Modifier.fillMaxWidth(0.6f)
        )
        Row(
            //modifier = Modifier.fillMaxWidth(0.5f)
        ){
            Button(onClick = {
                if(!isTextFieldEnabled.value){ isTextFieldEnabled.value= true
                }
                else {
                    isTextFieldEnabled.value= false
                    MyViewModel.getLocation(direction)
                    if(MyViewModel.show){
                        navController.navigate("MapsSearchView/${MyViewModel.lat}/${MyViewModel.long}/${MyViewModel.address}")
                    }
                }
                             },
                modifier = Modifier
                    .padding(1.dp),

                colors =
                if(isTextFieldEnabled.value)
                ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primaryContainer)
                else
                    ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiaryContainer)
                ) {
                Icon(
                    if(isTextFieldEnabled.value)Icons.Filled.Check
                    else Icons.Filled.Edit,
                    "Edit",
                )
            }
            Button(onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(1.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.errorContainer)

            ) {
                Icon(
                    Icons.Filled.Clear,
                    "Edit"
                )
            }
        }

    }
}
