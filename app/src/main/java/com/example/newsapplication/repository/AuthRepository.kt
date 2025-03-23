package com.example.newsapplication.repository
import com.example.newsapplication.ApiClient
import com.example.newsapplication.models.ApiResponse
import com.example.newsapplication.models.LoginRequest
import com.example.newsapplication.models.RegisterRequest
import retrofit2.Response

class AuthRepository {
    suspend fun register(username: String, email: String, password: String): Response<ApiResponse> {
        val request = RegisterRequest(username, email, password)
        return ApiClient.apiService.register(request)
    }

    suspend fun login(email: String, password: String): Response<ApiResponse> {
        val request = LoginRequest(email, password)
        return ApiClient.apiService.login(request)
    }
}
