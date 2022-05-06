package com.gurtam.news.data.repository

import com.gurtam.news.data.network.dto.HeadlineDto
import com.gurtam.news.data.network.dto.SourceDto
import com.gurtam.news.domain.entity.Headline
import com.gurtam.news.domain.entity.Source

fun SourceDto.toDomain() = Source(id, name, description, url, category, language, country)

fun HeadlineDto.toDomain() = Headline(
    author,
    title,
    description,
    url,
    urlToImage,
    publishedAt,
    content ?: "",
    source.id,
    source.name
)