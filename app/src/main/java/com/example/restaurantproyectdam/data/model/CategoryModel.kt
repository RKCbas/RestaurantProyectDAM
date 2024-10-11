package com.example.restaurantproyectdam.data.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.restaurantproyectdam.R

data class CategoryModel(
    val id:Int,
    val name:String,
    val image:Painter
)
@Composable
fun createArrayCategories():Array<CategoryModel>{
    val arrayCategories = arrayOf(
        CategoryModel(1,"Test 1", painterResource(R.drawable.sushi)),
        CategoryModel(2,"Test 2", painterResource(R.drawable.sushi)),
        CategoryModel(3,"Test 3", painterResource(R.drawable.sushi)),
        CategoryModel(4,"Test 4", painterResource(R.drawable.sushi)),
        CategoryModel(5,"Categoría", painterResource(R.drawable.sushi)),
        CategoryModel(6,"Categoría", painterResource(R.drawable.sushi)),
        CategoryModel(7,"Categoría", painterResource(R.drawable.sushi)),
        CategoryModel(8,"Categoría", painterResource(R.drawable.sushi)),
        CategoryModel(9,"Categoría", painterResource(R.drawable.sushi)),
    )
    return arrayCategories
}
