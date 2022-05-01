package com.gurtam.task.data.network.dto

data class ArticleResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleDto>
)
