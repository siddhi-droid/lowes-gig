package com.siddhi.lowesgig.network

import com.siddhi.lowesgig.data.remote.HeadlinesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET(Endpoints.NewsHeadlines)
    fun getHeadlines(
        @Query("country") country: String?,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
        //@Query("apiKey") apiKey: String?
    ): Single<HeadlinesResponse>
}