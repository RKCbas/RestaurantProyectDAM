package com.example.restaurantproyectdam.ui.components.maps


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapsSearchView(lat: Double=21.8853, long: Double= -102.2916, address: String= "Aguascalientes, México",navController: NavController){
    val place = LatLng(lat, long) //Coordenadas de lat  y long
    val markerState = rememberMarkerState(position = place)
    val cameraPosition = CameraPosition.fromLatLngZoom(place, 10f)
    val cameraState = rememberCameraPositionState {
        position = cameraPosition
    }

    Box(modifier = Modifier
        .fillMaxSize()
    ){
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraState
        ){
            MarkerInfoWindow( //Marcador en el lugar especificado
                state = markerState){
                CardMarker(address = address)
            }
        }
        Row(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
        ){
            Button(
                onClick = {
                    //ADD THIS ADDRESS
                        navController.popBackStack()
                          },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primaryContainer)
            ){
                Icon(Icons.Filled.Check, contentDescription = "")
            }
            Button(
                onClick = {
                    //Cancel
                    navController.popBackStack()
                          },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.errorContainer)
            ){
                Icon(Icons.Filled.Clear, contentDescription = "")
            }
        }

    }
}

@Composable
fun CardMarker(address: String) {
    Card(
        modifier = Modifier
            .padding(18.dp)
            .height(150.dp)
    )
    {
        Column(
            modifier = Modifier
                .padding(18.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Icon(imageVector = Icons.Default.LocationOn,
                contentDescription = "",
                modifier = Modifier.size(40.dp)
            )
            Text(text = address,
                modifier = Modifier.padding(all = 15.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}