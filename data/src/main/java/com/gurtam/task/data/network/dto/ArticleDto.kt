package com.gurtam.task.data.network.dto

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class ArticleDto(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: LocalDateTime?,
    val title: String?,
    val url: String,
    @SerializedName("source") val newsSource: NewsSourceDto,
    @SerializedName("urlToImage") val imageUrl: String?
)