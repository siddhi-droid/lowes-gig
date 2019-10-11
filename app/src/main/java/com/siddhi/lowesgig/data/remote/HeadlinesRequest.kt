package com.siddhi.lowesgig.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class HeadlinesRequest(
    @Expose
    @SerializedName("in")
    var email: String,

    @Expose
    @SerializedName("apiKey")
    val apiKey: String
)