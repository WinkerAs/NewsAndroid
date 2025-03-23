package com.example.newsapplication.repository


import com.example.newsapplication.ApiClient
import com.example.newsapplication.models.ApiResponseNews
import retrofit2.Response

class NewsRepository {
    suspend fun getNews(): Response<ApiResponseNews> {
        return ApiClient.apiService.getNews()
    }
}
