package com.example.newsapplication.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.models.ApiResponseNews
import com.example.newsapplication.models.News
import com.example.newsapplication.repository.NewsRepository

import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel : ViewModel() {
    private val repository = NewsRepository()

    private val _newsResponse = MutableLiveData<Response<ApiResponseNews>>()
    val newsResponse: LiveData<Response<ApiResponseNews>> get() = _newsResponse

    private val _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>> get() = _newsList

    fun fetchNews() {
        viewModelScope.launch {
            val response = repository.getNews()
            _newsResponse.postValue(response)
            if (response.isSuccessful) {
                _newsList.postValue(response.body()?.news ?: listOf())
            }
        }
    }
}
