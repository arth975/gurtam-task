package com.gurtam.task.domain.repositories

import kotlinx.coroutines.flow.Flow

interface ArticleRepository {

    suspend fun getArticlesBySourceId(sourceId: String): Flow<*>
}