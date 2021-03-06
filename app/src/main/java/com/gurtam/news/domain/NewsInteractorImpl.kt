package com.gurtam.news.domain

import androidx.paging.PagingSource
import com.gurtam.news.data.repository.NewsRepository
import com.gurtam.news.domain.entity.Headline
import com.gurtam.news.domain.entity.Source
import javax.inject.Inject

class NewsInteractorImpl @Inject constructor(
    private val newsRepository: NewsRepository
) : NewsInteractor {

    override suspend fun getSources(): List<Source> {
        return newsRepository.getSources()
    }

    override suspend fun getHeadlines(source: Source): List<Headline> {
        return newsRepository.getHeadlines(source)
    }

    override fun getHeadlinesDataSource(source: Source): PagingSource<Int, Headline> {
        return newsRepository.getHeadlinesDataSource(source)
    }
}