package com.example.restaurantproyectdam.data.controller

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantproyectdam.data.database.AppDatabase
import com.example.restaurantproyectdam.data.model.DishEntity
import com.example.restaurantproyectdam.data.model.DishModel
import com.example.restaurantproyectdam.data.model.toDishEntityList
import com.example.restaurantproyectdam.data.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class DishViewModel: ViewModel() {
    val api = RetrofitClient.api

    fun getDishes(db: AppDatabase){
        val dishDao = db.dishDao()
        viewModelScope.launch {
            try {
                val response = api.getDishes()
                print(response)
                if (response.body()?.count()!! > 0) {
                    val dishEntities = response.body()?.toDishEntityList()
                    if (dishEntities != null) {
                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                dishDao.insertDishes(dishEntities)
                            } catch (exception: Exception) {
                                Log.d("error", exception.toString())
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                print(e)
            }
        }
    }

    fun showDish(dishId: Int, onResult:(Response<DishModel>) -> Unit){
        viewModelScope.launch {
            try {
                val response = api.getDish(dishId)
                onResult(response)
            } catch (e: Exception) {
                print(e)
            }
        }
    }
}