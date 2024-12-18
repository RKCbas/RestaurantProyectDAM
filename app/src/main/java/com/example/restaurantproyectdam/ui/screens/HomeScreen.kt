package com.example.restaurantproyectdam.ui.screens

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import android.widget.Toast

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.restaurantproyectdam.CaptureActivityPortrait
import com.example.restaurantproyectdam.R
import com.example.restaurantproyectdam.ui.components.BottomBar
import com.example.restaurantproyectdam.ui.components.Material3SearchBar
import com.example.restaurantproyectdam.ui.components.homecomponents.PagerScreen

import com.example.restaurantproyectdam.ui.components.homecomponents.PermissionRequiredDialog
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import com.example.restaurantproyectdam.data.controller.CategoryViewModel
import com.example.restaurantproyectdam.data.database.AppDatabase
import com.example.restaurantproyectdam.data.database.DatabaseProvider
import com.example.restaurantproyectdam.data.model.CategoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.restaurantproyectdam.data.controller.DishViewModel
import com.example.restaurantproyectdam.data.controller.UserViewModel
import com.example.restaurantproyectdam.data.model.DishEntity


//@OptIn(ExperimentalMaterial3Api::class)

var myNavController: NavController? = null;
var myUserId: Int? = null;


@Composable

fun HomeScreen(
    navController: NavController,
    userViewModel: UserViewModel,
    categoryViewModel: CategoryViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    dishViewModel: DishViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val context = LocalContext.current

    val db: AppDatabase = DatabaseProvider.getDatabase(LocalContext.current)
    val categoryDao = db.categoryDao()
    val dishDao = db.dishDao()

    var categories by remember { mutableStateOf<List<CategoryEntity>>(emptyList()) }
    var dishes by remember { mutableStateOf<List<DishEntity>>(emptyList()) }


    //val scrollState = rememberScrollState()

    myNavController = navController
    myUserId = userViewModel.userId
    println(myUserId)

    println("carrito:" + userViewModel.cartId)

    // Stores the dimensions of the actual screen
    var WindowsSize = currentWindowAdaptiveInfo().windowSizeClass
    //Sets variables with the height and width of the screen
    var height = currentWindowAdaptiveInfo().windowSizeClass.windowHeightSizeClass
    var width = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass

    Scaffold(
        //color = Color.White
        bottomBar = { BottomBar(navController = navController) },
        //floatingActionButton = { SearchButton(onClick = {}) }
    ) { innerPadding ->

        LaunchedEffect(Unit) {
            categories = withContext(Dispatchers.IO) {
                categoryViewModel.getCategories(db)
                categoryDao.getCategories()
            }
            dishes = withContext(Dispatchers.IO) {
                dishViewModel.getDishes(db)
                dishDao.getDishes()
            }

        }

        val listState = rememberLazyListState()

        Column(
            modifier = Modifier
                .padding(innerPadding)

            //.verticalScroll(scrollState)

        ) {
            if (width == WindowWidthSizeClass.COMPACT) {
                //PORTAIT
                ContentPortrait(categories, dishes, userViewModel, context)

            } else if (height == WindowHeightSizeClass.COMPACT) {
                //LANDSCAPE
                //Posts(post, "PhoneL") //PhoneP = Phone LANDSCAPE
                ContentLandscape(categories, dishes, userViewModel, context)
            } else {

                //Posts(post, "PhoneL")
                ContentLandscape(categories, dishes, userViewModel, context)
            }
            //Content()
        }
    }
}


@Composable
private fun ContentPortrait(
    categories: List<CategoryEntity>,
    dishes: List<DishEntity>,
    userViewModel: UserViewModel,
    context: Context
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
        //.background(MaterialTheme.colorScheme.primaryContainer)
        //.padding(25.dp)
    ) {
        HeaderPortrait(true, userViewModel, context)
        MainContent(categories, dishes)
    }

}


@Composable
private fun ContentLandscape(
    categories: List<CategoryEntity>,
    dishes: List<DishEntity>,
    userViewModel: UserViewModel,
    context: Context
) {

    Row(
        modifier = Modifier
            .fillMaxSize()
        //.background(MaterialTheme.colorScheme.primaryContainer)
        //.padding(25.dp)
    ) {
        HeaderPortrait(false, userViewModel, context)
        MainContent(categories, dishes)
    }

}


//@Preview(showBackground=true)
@Composable
private fun CategoryItem(category_id: Int, name: String, category_image: String?) {

    Button(
        // Pattern that applies to all the 10 items
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .padding(10.dp, 5.dp, 5.dp, 0.dp)
            .clip(RoundedCornerShape(10.dp)) // Like border radius
            .background(color = MaterialTheme.colorScheme.surface),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface),
        //color = MaterialTheme.colorScheme.surface
        //.background(Color.White),
        //elevation = 6.dp // adds a shadow
        onClick = {
            myNavController?.navigate("categoryProducts/${category_id}")
        },
        //MaterialTheme.colorScheme.surface
        //ButtonDefaults.buttonColors(Primary)
        //colors = MaterialTheme.colorScheme.surface,
        //colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.surface),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp), // Mimicking Card elevation
        shape = MaterialTheme.shapes.medium, // Use the shape of a Card
        //contentPadding = PaddingValues(16.dp) // You can adjust padding to match a Card's style
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp),
            //.background(color = MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = category_image,
                //painterResource(id = R.drawable.sushi),
                contentDescription = name,
                //contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            //Spacer(modifier = Modifier.padding(5.dp)) //Leaves some space

            Text(
                text = name,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                //color=Color.Black,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
private fun MainContent(categoryList: List<CategoryEntity>, dishes: List<DishEntity>) {
    LazyColumn(modifier = Modifier.fillMaxHeight(1f)) {
        item {
            Text(
                text = "Categories",
                modifier = Modifier.padding(10.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
        item {
            LazyRow {
                items(categoryList) { category ->
                    CategoryItem(category.category_id, category.name, category.category_image)
                }
            }
        }

        item {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Suggestions",
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                myNavController?.let { PagerScreen(it, myUserId, dishes) }
            }

        }
        item {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Favorites",
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                myNavController?.let { PagerScreen(it, myUserId, dishes) }
            }
        }
        item {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Most Popular",
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                myNavController?.let { PagerScreen(it, myUserId, dishes) }
            }

        }


    }
}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun HeaderPortrait(vertical: Boolean, userViewModel: UserViewModel, context: Context) {

    var selectedOption: Int by remember {
        mutableIntStateOf(R.string.pick_up)
    }

    val permissions = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.CAMERA
        )
    )

    var showPermissionDialog by remember { mutableStateOf(false) }

    val scanLauncher = rememberLauncherForActivityResult(
        contract = ScanContract(),
        onResult = { result ->
            val aux = result.contents ?: ""

            val tabId: Int? = try {
                aux.toInt()
            } catch (e: NumberFormatException) {
                // Mostramos un Toast si la conversión falla
                Toast.makeText(context, "QR inválido", Toast.LENGTH_SHORT).show()
                null // Retornamos null si la conversión falla
            }

            tabId?.let {
                selectedOption = R.string.eat_here
            }

            userViewModel.updateTableId(tabId)

        }
    )

    Column(
        modifier =
        if (vertical) Modifier.fillMaxWidth(1f) else Modifier.fillMaxWidth(0.5f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(R.drawable.logo_fuji),
            contentDescription = "logo",
            modifier =
            if (vertical) Modifier
                .width(170.dp)
                .height(100.dp)
            else Modifier
                .width(85.dp)
                .height(50.dp),
            tint = MaterialTheme.colorScheme.inversePrimary // Set the color you want here
        )
        Text(
            "JAPANESE",
            style = MaterialTheme.typography.titleLarge,
            //color = MaterialTheme.colorScheme.inversePrimary,
            //fontSize = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            "Restaurant",
            style = MaterialTheme.typography.titleSmall,
            //color = MaterialTheme.colorScheme.inversePrimary,
        )

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(top = 4.dp, bottom = 0.dp)
        ) {
            listOf(R.string.eat_here, R.string.pick_up).forEach { option ->

                Button(
                    onClick = {
                        if (option == R.string.eat_here) {
                            if (permissions.allPermissionsGranted) {
                                val scanOptions = ScanOptions()
                                scanOptions.setBeepEnabled(true)
                                scanOptions.setCaptureActivity(CaptureActivityPortrait::class.java)
                                scanOptions.setOrientationLocked(false)
                                scanLauncher.launch(scanOptions)
                            } else {
                                showPermissionDialog = true
                            }
                        } else {
                            selectedOption = option
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedOption == option) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.surface,
                        contentColor = if (selectedOption == option) MaterialTheme.colorScheme.onPrimary
                        else MaterialTheme.colorScheme.onSurface
                    ),
                    modifier = Modifier.padding(end = 2.dp)
                ) {
                    Text(text = stringResource(id = option))
                }

            }
        }

        // Mostrar el diálogo si `showPermissionDialog` es verdadero
        if (showPermissionDialog) {
            PermissionRequiredDialog(
                onDismissRequest = { showPermissionDialog = false },
                onConfirmRequest = {
                    // Solicitar permisos de cámara y cerrar el diálogo
                    permissions.launchMultiplePermissionRequest()
                    showPermissionDialog = false
                }
            )
        }


        //Temporal arrangement before we implement the QR
        Text(
            text =
            if (userViewModel.tableId != null) {
                userViewModel.tableId.toString()
            } else {
                "comer aquí"
            },
            modifier = Modifier.padding(0.dp)
        )


        var query by remember { mutableStateOf("") }
        /*Material3SearchBar(
            modifier = Modifier.padding(top = 0.dp),
            query = query,
            onQueryChanged = { newQuery ->
                query = newQuery
            },
            onSearch = { searchQuery ->
                // Perform search logic
            }
        )*/
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController(), viewModel())
}

