package com.gurtam.news.data.repository

import com.gurtam.news.data.network.NewsService
import com.gurtam.news.domain.entity.Headline
import com.gurtam.news.domain.entity.Source
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsService: NewsService
) : NewsRepository {

    override suspend fun getSources(): List<Source> {
        return newsService.getSources().sources.map { it.toDomain() }
    }

    override suspend fun getHeadlines(source: Source): List<Headline> {
        return newsService.getHeadlines(source.id).articles.map { it.toDomain() }
    }

}