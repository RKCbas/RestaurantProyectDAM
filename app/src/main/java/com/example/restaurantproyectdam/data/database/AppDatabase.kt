package com.example.restaurantproyectdam.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.restaurantproyectdam.data.dao.CategoryDao
import com.example.restaurantproyectdam.data.dao.DishDao
import com.example.restaurantproyectdam.data.model.CategoryEntity
import com.example.restaurantproyectdam.data.model.DishEntity

@Database(entities = [CategoryEntity::class, DishEntity::class], version = 2)
abstract class AppDatabase: RoomDatabase(){
    abstract fun categoryDao(): CategoryDao
    abstract fun dishDao(): DishDao
}