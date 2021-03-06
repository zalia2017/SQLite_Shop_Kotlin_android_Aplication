package com.example.navigasiapp.api

import com.example.navigasiapp.model.CategoryModel
import com.example.navigasiapp.model.DefaultResponse
import com.example.navigasiapp.model.LoginResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email:String,
        @Field("password") password:String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<DefaultResponse>

    @GET("categories")
    fun getCategories(@Header("Authorization") authHeader:String): Call<CategoryModel>
}