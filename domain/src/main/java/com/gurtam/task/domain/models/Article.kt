package com.gurtam.task.domain.models

import java.time.LocalDateTime

data class Article(
    val title: String?,
    val description: String?,
    val publishedDate: LocalDateTime?,
    val imageUrl: String?,
    val url: String?
)
