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
        CategoryModel(1,"Tempura", painterResource(R.drawable.tempura)),
        CategoryModel(2,"Desserts", painterResource(R.drawable.dango)),
        CategoryModel(3,"Onigiri", painterResource(R.drawable.onigiri)),
        CategoryModel(4,"Sushi", painterResource(R.drawable.sushi)),
        CategoryModel(5,"Categoría", painterResource(R.drawable.sushi)),
        CategoryModel(6,"Categoría", painterResource(R.drawable.sushi)),
        CategoryModel(7,"Categoría", painterResource(R.drawable.sushi)),
        CategoryModel(8,"Categoría", painterResource(R.drawable.sushi)),
        CategoryModel(9,"Categoría", painterResource(R.drawable.sushi)),
    )
    return arrayCategories
}
