package com.example.practicearquitecture.data

import com.example.practicearquitecture.data.model.ListProduct
import com.example.practicearquitecture.utils.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WebService {
    @GET("products")
    suspend fun getProducts(): ListProduct
}

object RetrofitClient{

    val webservice by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}