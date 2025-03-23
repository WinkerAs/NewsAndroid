package com.example.newsapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.models.News
import com.squareup.picasso.Picasso

class NewsAdapter(private var newsList: List<News>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    fun updateNews(list: List<News>) {
        newsList = list
        notifyDataSetChanged()
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.findViewById(R.id.textViewTitle)
        val textText: TextView = itemView.findViewById(R.id.textViewText)
        val imageView: ImageView = itemView.findViewById(R.id.imageViewNews)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]
        holder.titleText.text = news.title
        holder.textText.text = news.text
        // Загрузка изображения (предполагается, что news.image – URL)
        Picasso.get().load(news.image).into(holder.imageView)
    }

    override fun getItemCount(): Int = newsList.size
}
