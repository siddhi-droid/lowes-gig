package com.siddhi.lowesgig.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.siddhi.lowesgig.data.local.db.DBConstants

@Entity(tableName = DBConstants.NEWS_TABLE_NAME)
data class NewsEntity(

    @field:SerializedName("publishedAt")
    @ColumnInfo(name = DBConstants.NEWS_PUBLISHED_AT)
    val publishedAt: String,


    @field:SerializedName("urlToImage")
    @ColumnInfo(name = DBConstants.IMAGE_URL)
    @PrimaryKey
    @NonNull
    val urlToImage: String,

    @field:SerializedName("description")
    @ColumnInfo(name = DBConstants.NEWS_DESCRIPTION)
    val description: String,

    @field:SerializedName("name")
    @ColumnInfo(name = DBConstants.NEWS_SOURCE_NAME)
    val name: String,

    @field:SerializedName("title")
    @ColumnInfo(name = DBConstants.NEWS_TITLE)
    val title: String
)