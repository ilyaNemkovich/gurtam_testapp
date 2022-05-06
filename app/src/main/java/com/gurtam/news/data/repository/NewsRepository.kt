package com.gurtam.news.data.repository

import com.gurtam.news.domain.entity.Headline
import com.gurtam.news.domain.entity.Source

interface NewsRepository {

    suspend fun getSources(): List<Source>
    suspend fun getHeadlines(source: Source): List<Headline>
}