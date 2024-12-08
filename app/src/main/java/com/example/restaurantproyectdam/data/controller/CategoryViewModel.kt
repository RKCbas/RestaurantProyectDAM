package com.example.restaurantproyectdam.data.controller

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantproyectdam.data.database.AppDatabase
import com.example.restaurantproyectdam.data.model.toCategoryEntityList
import com.example.restaurantproyectdam.data.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel: ViewModel() {

    val api = RetrofitClient.api

    fun getCategories(db: AppDatabase){
        val categoryDao = db.categoryDao()
        viewModelScope.launch {
            try{
                val response = api.getCategories()
                print(response)
                if (response.body()?.count()!! > 0){
                    val categoryEntities = response.body()?.toCategoryEntityList()
                    if (categoryEntities != null) {
                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                categoryDao.insertCategories(categoryEntities)
                            } catch (exception: Exception) {
                                Log.d("error", exception.toString())
                            }
                        }
                    }
                }

            }catch(e: Exception){
                print(e)
            }

        }

    }

}