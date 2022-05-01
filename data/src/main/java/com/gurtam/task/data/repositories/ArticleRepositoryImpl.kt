package com.gurtam.task.data.repositories

import com.gurtam.task.domain.repositories.ArticleRepository
import kotlinx.coroutines.flow.Flow

class ArticleRepositoryImpl : ArticleRepository {
    override fun <T> fetchNewsBySourceId(sourceId: String): Flow<T> {
        TODO()
    }
}