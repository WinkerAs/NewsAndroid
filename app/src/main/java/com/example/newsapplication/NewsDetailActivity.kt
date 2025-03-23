package com.example.newsapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import com.example.newsapplication.models.NewsModel
import com.squareup.picasso.Picasso

class NewsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val news = intent.getParcelableExtra<NewsModel>("news")
        news?.let {
            val imageView = findViewById<ImageView>(R.id.imageViewNewsDetail)
            val titleView = findViewById<TextView>(R.id.textViewTitleDetail)
            val dateView = findViewById<TextView>(R.id.textViewDateDetail)
            val textView = findViewById<TextView>(R.id.textViewTextDetail)

            Picasso.get().load(it.image).into(imageView)
            titleView.text = it.title
            textView.text = it.text
            dateView.text = it.created_at
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
} 