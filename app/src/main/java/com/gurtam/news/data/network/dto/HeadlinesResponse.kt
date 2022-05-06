package com.gurtam.news.data.network.dto

data class HeadlinesResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<HeadlineDto>
)

data class HeadlineDto(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String?,
    val source: SourceShortDto,
)

data class SourceShortDto(
    val id: String,
    val name: String
)