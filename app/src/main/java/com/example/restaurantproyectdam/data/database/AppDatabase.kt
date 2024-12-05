package com.example.projecto1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.projecto1.data.dao.ServiceDao
//import com.example.projecto1.data.model.ServiceEntity


//@Database(entities = [ServiceEntity::class], version=1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun serviceDao(): ServiceDao
}