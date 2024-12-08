package com.example.restaurantproyectdam.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.restaurantproyectdam.data.model.DishEntity

@Dao
interface DishDao{

    @Query("SELECT * FROM DishEntity")
    suspend fun getDishes(): List<DishEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDishes(dishes: List<DishEntity>)

    @Query("SELECT * FROM DishEntity WHERE dish_id = :dishId")
    suspend fun getDish(dishId: Int): DishEntity

}