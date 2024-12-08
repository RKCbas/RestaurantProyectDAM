package com.example.restaurantproyectdam.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class DishModel(
    val dish_id: Int = 0,
    val name :String ="",
    val description: String = "",
    val price: Float =0.0f,
    val ingredients: String = "",
    val dish_image: String ="",
    val preparation_time: Int =0,
    val category_id: Int = 0,
    val created_at: String = "",
    val updated_at: String = ""
)

data class DishWithPivot(
    val dish_id: Int = 0,
    val name :String ="",
    val description: String = "",
    val price: Float =0.0f,
    val ingredients: String = "",
    val dish_image: String ="",
    val prepartion_time: Int =0,
    val created_at: String = "",
    val updated_at: String = "",
    val pivot: Pivot
)
data class Pivot(
    val cart_id: Int = 0,
    val dish_id: Int = 0,
    val quantity: Int = 0,
    val total_price: Float = 0.0f,
    val created_at: String="",
    val updated_at:String =""
)

@Entity
data class DishEntity(
    @PrimaryKey val dish_id:Int,
    @ColumnInfo(name="name") val name:String,
    @ColumnInfo(name="description") val description:String,
    @ColumnInfo(name="price") val price:Float,
    @ColumnInfo(name="ingredients") val ingredients:String,
    @ColumnInfo(name="dish_image") val dish_image:String,
    @ColumnInfo(name="preparation_time") val preparation_time:Int,
    @ColumnInfo(name="category_id") val category_id:Int,
    @ColumnInfo(name="created_at") val created_at:String,
    @ColumnInfo(name="updated_at") val updated_at:String
)

fun DishModel.toDishEntity(): DishEntity{
    return DishEntity(
        dish_id = this.dish_id,
        name = this.name,
        description = this.description,
        price = this.price,
        ingredients = this.ingredients,
        dish_image = this.dish_image,
        preparation_time = this.preparation_time,
        category_id = this.category_id,
        created_at = this.created_at,
        updated_at = this.updated_at
    )
}

fun List<DishModel>.toDishEntityList(): List<DishEntity>{
    return this.map { it.toDishEntity() }
}


