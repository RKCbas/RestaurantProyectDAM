package com.example.restaurantproyectdam.data.network

import com.example.restaurantproyectdam.data.model.AddToCartModelRequest
import com.example.restaurantproyectdam.data.model.CartModelActionResponse
import com.example.restaurantproyectdam.data.model.CartModelRequest
import com.example.restaurantproyectdam.data.model.CartModelResponse
import com.example.restaurantproyectdam.data.model.CartModelWithContent
import com.example.restaurantproyectdam.data.model.CartOnlyContent
import com.example.restaurantproyectdam.data.model.CategoryModel
import com.example.restaurantproyectdam.data.model.DeleteCartModelResponse
import com.example.restaurantproyectdam.data.model.DishModel
import com.example.restaurantproyectdam.data.model.DishWithPivot
import com.example.restaurantproyectdam.data.model.LoginRequest
import com.example.restaurantproyectdam.data.model.LoginResponse
import com.example.restaurantproyectdam.data.model.RegisterRequest
import com.example.restaurantproyectdam.data.model.RegisterResponse
import com.example.restaurantproyectdam.data.model.ShowCartModelResponse
import com.example.restaurantproyectdam.data.model.User
import com.example.restaurantproyectdam.data.model.UserUpdateRequest
import com.example.restaurantproyectdam.data.model.UserUpdateResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path



interface ApiService {
    //User
    //login
    @POST("users/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    //register
    @POST("users")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    //Update
    @PUT("users/{user_id}")
    suspend fun updateUser(
        @Path("user_id") user_id : Int,
        @Body userUpdateRequest: UserUpdateRequest
    ): Response<UserUpdateResponse>

    //Show user
    @GET("users/{user_id}")
    suspend fun  showUser(
        @Path("user_id") user_id : Int
    ): Response<User>
/////////////////////////////////////////////////////////////////////////////////////
    //Cart
    //Creation
    @POST("carts")
    suspend fun createCart(@Body cart: CartModelRequest): Response<CartModelResponse>

    @GET("carts/{id}")
    suspend fun showCart(@Path("id") id : Int): Response<ShowCartModelResponse>


    @DELETE("carts/{id}")
    suspend fun deleteCart(@Path("id") id :Int): Response<DeleteCartModelResponse>

    @POST("carts/{cart_id}/dishes/{dish_id}")
    suspend fun addToCart(
        @Path("cart_id") cart_id : Int,
        @Path("dish_id") dish_id: Int,
        @Body addToCartModel : AddToCartModelRequest
    ): Response<CartModelActionResponse>


    @GET("carts/{cart_id}/dishes")
    suspend fun showCartContent(@Path("cart_id") cart_id: Int): Response<CartOnlyContent>

    @DELETE("carts/{cart_id}/dishes/{dish_id}")
    suspend fun removeDishFromCart(
        @Path("cart_id") cart_id : Int,
        @Path("dish_id") dish_id: Int
    ): Response<CartModelActionResponse>

    //Categories
    @GET("categories")
    suspend fun getCategories(): Response<List<CategoryModel>>
    //Dishes
    @GET("dishes")
    suspend fun getDishes(): Response<List<DishModel>>

    @GET("dishes/{id}")
    suspend fun getDish(@Path("id") id : Int): Response<DishModel>

}