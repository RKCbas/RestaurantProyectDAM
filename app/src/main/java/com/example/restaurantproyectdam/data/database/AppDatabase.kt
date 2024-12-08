package com.example.restaurantproyectdam.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.restaurantproyectdam.data.dao.CategoryDao
import com.example.restaurantproyectdam.data.model.CategoryEntity

@Database(entities = [CategoryEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun categoryDao(): CategoryDao
}