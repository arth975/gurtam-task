package com.gurtam.task.data.network.dto

data class NewsSourceDto(
    val id: String,
    val name: String?,
    val description: String?,
    val url: String?,
    val category: String?,
    val language: String?,
    val country: String?
)
