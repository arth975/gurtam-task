package com.gurtam.task.data.network.dto

data class NewsSourceResponse(
    val status: String,
    val sources: List<NewsSourceDto>
)