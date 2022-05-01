package com.gurtam.task.data.repositories.sources.newssource

import com.gurtam.task.data.network.dto.NewsSourceDto
import kotlinx.coroutines.flow.Flow

interface NewsSourceRemoteDataSource {

    suspend fun fetchAll(): List<NewsSourceDto>
}