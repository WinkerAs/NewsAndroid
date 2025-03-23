package com.example.newsapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


import android.widget.Toast
import androidx.activity.viewModels

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.viewmodel.NewsViewModel


class NewsActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewNews)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NewsAdapter(listOf())
        recyclerView.adapter = adapter

        newsViewModel.newsList.observe(this) { newsList ->
            adapter.updateNews(newsList)
        }

        newsViewModel.newsResponse.observe(this) { response ->
            if (!response.isSuccessful) {
                Toast.makeText(this, "Ошибка загрузки новостей", Toast.LENGTH_SHORT).show()
            }
        }

        newsViewModel.fetchNews()
    }
}
