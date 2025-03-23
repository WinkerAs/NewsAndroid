package com.example.newsapplication.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsModel(
    val id: Int,
    val title: String,
    val text: String,
    val image: String,
    val created_at: String
) : Parcelable