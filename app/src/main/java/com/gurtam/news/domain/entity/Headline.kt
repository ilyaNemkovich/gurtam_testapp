package com.gurtam.news.domain.entity

data class Headline(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
    val sourceId: String,
    val sourceName: String
)