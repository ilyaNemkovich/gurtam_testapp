package com.gurtam.news.domain

import androidx.paging.PagingSource
import com.gurtam.news.domain.entity.Headline
import com.gurtam.news.domain.entity.Source

interface NewsInteractor {

    suspend fun getSources(): List<Source>
    suspend fun getHeadlines(source: Source): List<Headline>
    fun getHeadlinesDataSource(source: Source): PagingSource<Int, Headline>
}