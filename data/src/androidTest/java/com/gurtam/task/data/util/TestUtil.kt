package com.gurtam.task.data.util

import com.gurtam.task.data.local.entites.ArticleEntity
import com.gurtam.task.data.network.dto.ArticleDto
import com.gurtam.task.data.network.dto.NewsSourceDto
import java.time.LocalDateTime

object TestUtil {

    fun createArticleEntity() = ArticleEntity(
        author = "Author",
        content = "Content",
        description = "Description",
        publishedAt = LocalDateTime.of(2022, 1, 1, 12, 0, 0),
        title = "Title",
        url = "Url",
        imageUrl = "Image Url",
        newsSourceId = "news-source-id"
    )

    fun createArticleDtoList(): List<ArticleDto> {
        val newsSourceDto = NewsSourceDto(
            id = "news-source-id",
            name = "News Source",
            description = null,
            url = null,
            category = null,
            language = null,
            country = null
        )

        return listOf(
            ArticleDto(
                author = "Author1",
                content = "Content1",
                description = "Description1",
                publishedAt = LocalDateTime.of(2022, 1, 1, 12, 0, 0),
                title = "Title1",
                url = "Url1",
                imageUrl = "Image Url1",
                newsSource = newsSourceDto
            ),
            ArticleDto(
                author = "Author2",
                content = "Content2",
                description = "Description2",
                publishedAt = LocalDateTime.of(2022, 1, 1, 12, 0, 0),
                title = "Title2",
                url = "Url2",
                imageUrl = "Image Url2",
                newsSource = newsSourceDto
            )
        )
    }
}