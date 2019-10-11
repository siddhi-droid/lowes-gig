package com.siddhi.lowesgig.data.repository

import com.siddhi.lowesgig.data.local.db.DatabaseService
import com.siddhi.lowesgig.network.NetworkService
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
)
