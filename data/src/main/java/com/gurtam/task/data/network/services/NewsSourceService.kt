package com.gurtam.task.data.network.services

import com.gurtam.task.data.network.Routes
import com.gurtam.task.data.network.dto.NewsSourceDto
import com.gurtam.task.data.network.dto.NewsSourceResponse
import retrofit2.http.GET

interface NewsSourceService {

    @GET(Routes.NEWS_SOURCES)
    suspend fun fetchAllNewsSources(): NewsSourceResponse
}