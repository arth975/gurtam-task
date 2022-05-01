package com.gurtam.task.domain.repositories

import com.gurtam.task.domain.models.ResultOf
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {

    fun <T> fetchNewsBySourceId(sourceId: String): Flow<T>
}