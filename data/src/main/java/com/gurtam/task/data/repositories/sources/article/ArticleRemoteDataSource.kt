package com.gurtam.task.data.repositories.sources.article

import com.gurtam.task.data.network.dto.ArticleDto

interface ArticleRemoteDataSource {

    suspend fun fetchArticlesBySourceIdAndPage(sourceId: String, page: Int): List<ArticleDto>
}