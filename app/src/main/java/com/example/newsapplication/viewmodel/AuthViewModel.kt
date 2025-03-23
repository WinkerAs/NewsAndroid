package com.example.newsapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.models.ApiResponse
import com.example.newsapplication.repository.AuthRepository

import kotlinx.coroutines.launch
import retrofit2.Response

class AuthViewModel : ViewModel() {
    private val repository = AuthRepository()

    private val _authResponse = MutableLiveData<Response<ApiResponse>>()
    val authResponse: LiveData<Response<ApiResponse>> get() = _authResponse

    fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            val response = repository.register(username, email, password)
            _authResponse.postValue(response)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val response = repository.login(email, password)
            _authResponse.postValue(response)
        }
    }
}
