package com.example.restaurantproyectdam.data.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.restaurantproyectdam.R

data class CategoryModel(
    var category_id:Int = 0,
    var name:String = "",
    var category_image:String? =null,
    var created_at:String = "",
    var updated_at:String = ""
)

@Entity
data class CategoryEntity(
    @PrimaryKey val category_id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "category_image") val category_image: String?,
    @ColumnInfo(name = "created_at") val created_at: String,
    @ColumnInfo(name = "updated_at") val updated_at: String
)

fun CategoryModel.toCategoryEntity(): CategoryEntity{
    return CategoryEntity(
        category_id = this.category_id,
        name = this.name,
        category_image = this.category_image,
        created_at = this.created_at,
        updated_at = this.updated_at
    )
}

fun List<CategoryModel>.toCategoryEntityList(): List<CategoryEntity>{
    return this.map { it.toCategoryEntity() }
}



@Composable
fun createArrayCategories():Array<CategoryModel>{
    val arrayCategories = arrayOf(
        CategoryModel(1, "Tempura", "https://media.istockphoto.com/id/1336067008/es/vector/icono-simple-de-tres-engranajes.jpg?s=612x612&w=0&k=20&c=7GqoECJQG_oOsMGwypakXVlcr7vabmYDT2inQ2yQsm0=","",""),
        CategoryModel(2, "Desserts", "https://media.istockphoto.com/id/1336067008/es/vector/icono-simple-de-tres-engranajes.jpg?s=612x612&w=0&k=20&c=7GqoECJQG_oOsMGwypakXVlcr7vabmYDT2inQ2yQsm0=","",""),
        CategoryModel(3, "Onigiri", "https://media.istockphoto.com/id/1336067008/es/vector/icono-simple-de-tres-engranajes.jpg?s=612x612&w=0&k=20&c=7GqoECJQG_oOsMGwypakXVlcr7vabmYDT2inQ2yQsm0=","",""),
        CategoryModel(4, "Sushi", "https://media.istockphoto.com/id/1336067008/es/vector/icono-simple-de-tres-engranajes.jpg?s=612x612&w=0&k=20&c=7GqoECJQG_oOsMGwypakXVlcr7vabmYDT2inQ2yQsm0=","",""),
        CategoryModel(5, "Categoría", "https://media.istockphoto.com/id/1336067008/es/vector/icono-simple-de-tres-engranajes.jpg?s=612x612&w=0&k=20&c=7GqoECJQG_oOsMGwypakXVlcr7vabmYDT2inQ2yQsm0=","",""),
        CategoryModel(6, "Categoría", "https://media.istockphoto.com/id/1336067008/es/vector/icono-simple-de-tres-engranajes.jpg?s=612x612&w=0&k=20&c=7GqoECJQG_oOsMGwypakXVlcr7vabmYDT2inQ2yQsm0=","",""),
        CategoryModel(7, "Categoría", "https://media.istockphoto.com/id/1336067008/es/vector/icono-simple-de-tres-engranajes.jpg?s=612x612&w=0&k=20&c=7GqoECJQG_oOsMGwypakXVlcr7vabmYDT2inQ2yQsm0=","",""),
        CategoryModel(8, "Categoría", "https://media.istockphoto.com/id/1336067008/es/vector/icono-simple-de-tres-engranajes.jpg?s=612x612&w=0&k=20&c=7GqoECJQG_oOsMGwypakXVlcr7vabmYDT2inQ2yQsm0=","",""),
        CategoryModel(9, "Categoría", "https://media.istockphoto.com/id/1336067008/es/vector/icono-simple-de-tres-engranajes.jpg?s=612x612&w=0&k=20&c=7GqoECJQG_oOsMGwypakXVlcr7vabmYDT2inQ2yQsm0=","",""),
    )

    return arrayCategories
}
