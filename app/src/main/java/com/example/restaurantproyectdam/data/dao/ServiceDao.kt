package com.example.projecto1.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
//import com.example.projecto1.data.model.ServiceEntity


//@Dao
//interface ServiceDao {
//    @Query("SELECT * FROM ServiceEntity")
//    fun getAll(): List<ServiceEntity>
//
//    @Query("SELECT * FROM ServiceEntity WHERE id = :serviceId LIMIT 1")
//    fun show(serviceId: Int): ServiceEntity
//
//    @Query("SELECT id FROM ServiceEntity ORDER BY id DESC LIMIT 1")
//    fun lastId(): Int
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAll(services: List<ServiceEntity>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertOnly(service: ServiceEntity)
//
//    @Delete
//    fun delete(service: ServiceEntity)
//}