package com.siddhi.lowesgig.data.repository

import com.siddhi.lowesgig.data.local.db.DatabaseService
import com.siddhi.lowesgig.data.local.entity.NewsEntity
import com.siddhi.lowesgig.data.model.ArticlesItem
import com.siddhi.lowesgig.data.remote.HeadlinesResponse
import com.siddhi.lowesgig.network.NetworkService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeadlinesRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {
    fun getHeadlines(country: String?/*, apiKey: String*/): Single<HeadlinesResponse> {
        return networkService.getHeadlines(country)
            .map {
                it
            }
    }

    fun saveArticles(articlesResponse: List<ArticlesItem>) {
        articlesResponse.forEach {
            val news = NewsEntity(
                it.publishedAt,
                it.urlToImage,
                it.description,
                it.source!!.name,
                it.title
            )
            databaseService.newsDao().insertAll(listOf(news))
        }
    }

    fun getHeadlinesFromRoomDb(): Single<List<NewsEntity>> {
        return databaseService.newsDao().getAll()
    }
}

