package com.siddhi.lowesgig.data.repository

import com.siddhi.lowesgig.data.local.db.DatabaseService
import com.siddhi.lowesgig.data.local.entity.NewsEntity
import com.siddhi.lowesgig.data.local.prefs.NewsPreferences
import com.siddhi.lowesgig.data.model.ArticlesItem
import com.siddhi.lowesgig.network.NetworkService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeadlinesRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
    private val newsPreferences: NewsPreferences
) {
    fun getHeadlines(country: String?/*, apiKey: String*/): Single<List<ArticlesItem>> =
        networkService.getHeadlines(country)
            .map {
                it.status
                it.totalResults
                it.articles
            }

    fun saveArticles(headlinesResponse: List<NewsEntity>) =
        databaseService.newsDao().insertAll(headlinesResponse)

    fun getHeadlinesFromRoomDb() =
        databaseService.newsDao().getAll()
}