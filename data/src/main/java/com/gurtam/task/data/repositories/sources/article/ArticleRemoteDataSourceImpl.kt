package com.gurtam.task.data.repositories.sources.article

import com.gurtam.task.data.network.dto.ArticleDto
import com.gurtam.task.data.network.services.ArticleService

class ArticleRemoteDataSourceImpl(
    private val articlesService: ArticleService
) : ArticleRemoteDataSource {
    override suspend fun fetchArticlesBySourceIdAndPage(sourceId: String, page: Int): List<ArticleDto> {
        return articlesService.fetchNewsBySourceIdAndPage(page, sourceId).articles
    }
}