package com.gurtam.task.data.repositories.sources.newssource

import com.gurtam.task.data.network.dto.NewsSourceDto
import com.gurtam.task.data.network.services.NewsSourceService

class NewsSourceRemoteDataSourceImpl(
    private val newsSourceService: NewsSourceService
) : NewsSourceRemoteDataSource {

    override suspend fun fetchAll(): List<NewsSourceDto> {
        return newsSourceService.fetchAllNewsSources().sources
    }

}