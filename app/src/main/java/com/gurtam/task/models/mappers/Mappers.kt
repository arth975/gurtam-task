package com.gurtam.task.models.mappers

import com.gurtam.task.domain.models.Article
import com.gurtam.task.domain.models.NewsSource
import com.gurtam.task.models.ArticleUI
import com.gurtam.task.models.NewsSourceUI

fun NewsSource.toUI() = NewsSourceUI(
    id = this.id,
    name = this.name,
    description = this.description
)

fun Article.toUI() = ArticleUI(
    title = this.title,
    description = this.description,
    publishDate = this.publishedDate,
    imageUrl = this.imageUrl,
    url = this.url
)