package com.siddhi.lowesgig.data.remote

import com.google.gson.annotations.SerializedName
import com.siddhi.lowesgig.data.model.ArticlesItem

data class HeadlinesResponse(
    @field:SerializedName("totalResults")
    val totalResults: Int,

    @field:SerializedName("articles")
    val articles: List<ArticlesItem>,

    @field:SerializedName("status")
    val status: String
)