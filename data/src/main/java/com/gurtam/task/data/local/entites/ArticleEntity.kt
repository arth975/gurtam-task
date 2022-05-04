package com.gurtam.task.data.local.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.time.LocalDateTime

@Entity(tableName = "articles")
data class ArticleEntity(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: LocalDateTime?,
    val title: String?,
    @PrimaryKey(autoGenerate = false) val url: String,
    val imageUrl: String?,
    val newsSourceId: String
)