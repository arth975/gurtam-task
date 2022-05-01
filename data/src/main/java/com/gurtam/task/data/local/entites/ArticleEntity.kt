package com.gurtam.task.data.local.entites

import androidx.room.Entity

@Entity(tableName = "articles")
data class ArticleEntity(
    val url: String
)
