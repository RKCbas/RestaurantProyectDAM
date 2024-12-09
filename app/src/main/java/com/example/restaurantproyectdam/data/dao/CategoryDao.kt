package com.example.restaurantproyectdam.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.restaurantproyectdam.data.model.CategoryEntity

@Dao
interface CategoryDao{
    @Query("SELECT * FROM CategoryEntity")
    suspend fun getCategories(): List<CategoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategories(categories: List<CategoryEntity>)
}