package com.gurtam.task.data.mappers

import com.gurtam.task.data.local.entites.ArticleEntity
import com.gurtam.task.data.local.entites.NewsSourceEntity
import com.gurtam.task.data.network.dto.ArticleDto
import com.gurtam.task.data.network.dto.NewsSourceDto
import com.gurtam.task.domain.models.Article
import com.gurtam.task.domain.models.NewsSource

fun NewsSourceDto.toEntity() = NewsSourceEntity(
    id = this.id,
    name = this.name,
    description = this.description,
    url = this.url,
    category = this.category,
    country = this.country,
    language = this.language
)

fun NewsSourceEntity.toDomain() = NewsSource(
    id = this.id,
    name = this.name,
    description = this.description,
    url = this.url,
    category = this.category,
    country = this.country,
    language = this.language
)

fun ArticleDto.toEntity() = ArticleEntity(
    author = this.author,
    content = this.content,
    description = this.description,
    publishedAt = this.publishedAt,
    title = this.title,
    url = this.url,
    imageUrl = this.imageUrl,
    newsSourceId = this.newsSource.id
)

fun ArticleEntity.toDomain() = Article(
    description = this.description,
    publishedDate = this.publishedAt,
    title = this.title,
    url = this.url,
    imageUrl = this.imageUrl
)