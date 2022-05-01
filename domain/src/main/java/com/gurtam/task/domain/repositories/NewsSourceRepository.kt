package com.gurtam.task.domain.repositories

import com.gurtam.task.domain.models.NewsSource
import com.gurtam.task.domain.models.ResultOf
import kotlinx.coroutines.flow.Flow

interface NewsSourceRepository {

    suspend fun getAll(): Flow<ResultOf<List<NewsSource>>>
}