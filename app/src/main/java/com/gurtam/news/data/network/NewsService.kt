package com.gurtam.news.data.network

import com.gurtam.news.data.network.dto.HeadlinesResponse
import com.gurtam.news.data.network.dto.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines/sources")
    suspend fun getSources(): SourcesResponse

    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("sources")
        sources: String,
        @Query("page")
        page: Int,
        @Query("pageSize")
        pageSize: Int = 4
    ): HeadlinesResponse
}