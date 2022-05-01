package com.gurtam.task.models

import java.time.LocalDateTime

data class NewsUI(
    val title: String?,
    val description: String?,
    val publishDate: LocalDateTime?,
    val imageUrl: String?,
    val url: String?
)
