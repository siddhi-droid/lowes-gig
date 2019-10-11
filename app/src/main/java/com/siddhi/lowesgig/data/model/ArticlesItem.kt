package com.siddhi.lowesgig.data.model

import androidx.annotation.NonNull
import com.google.gson.annotations.SerializedName

data class ArticlesItem(

    @field:SerializedName("publishedAt")
    val publishedAt: String,

    @field:SerializedName("author")
    val author: String,

    @field:SerializedName("urlToImage")
    val urlToImage: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("source")
    @NonNull
    val source: Source,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("content")
    val content: String
)