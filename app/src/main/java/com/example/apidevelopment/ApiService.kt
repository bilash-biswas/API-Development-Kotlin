package com.example.apidevelopment

import retrofit2.http.GET

interface ApiService {
    @GET("/posts")
    suspend fun getPost(): List<Post>
}