package com.example.newsapplication.models

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class ApiResponse(
    val status: String,
    val message: String,
    val user: User? = null
)

data class ApiResponseNews(
    val status: String,
    val news: List<NewsModel>?
)

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val created_at: String
)

