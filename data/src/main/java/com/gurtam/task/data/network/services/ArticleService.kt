package com.gurtam.task.data.network.services

import com.gurtam.task.data.network.Routes
import com.gurtam.task.data.network.dto.ArticleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {

    @GET(Routes.NEWS)
    suspend fun fetchNewsBySourceIdAndPage(
        @Query("page") page: Int,
        @Query("sources") source: String
    ): ArticleResponse
}