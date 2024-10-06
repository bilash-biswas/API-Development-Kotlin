package com.example.apidevelopment.student

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object StudentRetrofitClient {
    private const val BASE_URL = "http://192.168.0.103/StudentDatabase/"
    val gson = GsonBuilder().setLenient().create()
    val apiService : StudentApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(StudentApiService::class.java)
    }
}