package com.example.projecto1.data.controller

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projecto1.data.database.AppDatabase
//import com.example.projecto1.data.model.ServiceModel
//import com.example.projecto1.data.model.toServiceEntity
//import com.example.projecto1.data.model.toServiceEntityList
import kotlinx.coroutines.launch
import com.example.projecto1.data.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

//class ServiceViewModel : ViewModel() {
//    val api = RetrofitClient.api
//
//    fun getServices(db: AppDatabase) {
//
//        val serviceDao = db.serviceDao()
//
//        viewModelScope.launch {
//            try {
//                val response = api.getServices()
//                if (response.body()?.count()!! > 0) {
//                    val serviceEntities = response.body()?.toServiceEntityList()
//                    if (serviceEntities != null) {
//                        CoroutineScope(Dispatchers.IO).launch {
//                            try {
//                                serviceDao.insertAll(serviceEntities)
//                            } catch (exception: Exception) {
//                                Log.d("error", exception.toString())
//                            }
//                        }
//                    }
//                }
//            } catch (exception: Exception) {
//                print(exception)
//            }
//        }
//    }
//
//    fun showService(id: Int, onResult: (Response<ServiceModel>) -> Unit) {
//        viewModelScope.launch {
//            try {
//                val response = api.getService(id)
//                onResult(response)
//            } catch (exception: Exception) {
//                print(exception)
//            }
//        }
//    }
//
//    fun createService(
//        service: ServiceModel,
//        db: AppDatabase,
//        onResult: (Response<ServiceModel>) -> Unit
//    ) {
//
//        val serviceDao = db.serviceDao()
//        var lastID = -1
//
//        viewModelScope.launch {
//            try {
//                CoroutineScope(Dispatchers.IO).launch {
//                    lastID = serviceDao.lastId()
//                    service.id = lastID + 1
//                    serviceDao.insertOnly(service.toServiceEntity())
//                }
//                val response = api.createService(service)
//                onResult(response)
//            } catch (exception: Exception) {
//                println(exception)
//            }
//        }
//    }
//
//    fun updateService(
//        id: Int,
//        service: ServiceModel,
//        db: AppDatabase,
//        onResult: (Response<ServiceModel>) -> Unit
//    ) {
//
//        val serviceDao = db.serviceDao()
//
//        viewModelScope.launch {
//            try {
//                CoroutineScope(Dispatchers.IO).launch {
//                    service.id = id
//                    serviceDao.insertOnly(service.toServiceEntity())
//                }
//                val response = api.updateService(id, service)
//                onResult(response)
//            } catch (exception: Exception) {
//                println(exception)
//            }
//        }
//    }
//
//    fun deleteService(id: Int, onResult: (Response<ServiceModel>) -> Unit) {
//        viewModelScope.launch {
//            try {
//                val response = api.deleteService(id)
//                onResult(response)
//            } catch (exception: Exception) {
//                println(exception)
//            }
//        }
//    }
//
//}