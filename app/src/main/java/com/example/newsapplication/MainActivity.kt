package com.example.newsapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.viewmodel.NewsViewModel

class MainActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

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
