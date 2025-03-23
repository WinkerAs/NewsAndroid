package com.example.newsapplication


import com.example.newsapplication.models.ApiResponse
import com.example.newsapplication.models.ApiResponseNews
import com.example.newsapplication.models.LoginRequest
import com.example.newsapplication.models.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("api.php?action=register")
    suspend fun register(@Body request: RegisterRequest): Response<ApiResponse>

    @POST("api.php?action=login")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse>

    @GET("api.php?action=getNews")
    suspend fun getNews(): Response<ApiResponseNews>
}
