package com.gurtam.task.data.repositories.sources.newssource

import com.gurtam.task.data.local.entites.NewsSourceEntity

interface NewsSourceLocalDataSource {

    suspend fun fetchAll(): List<NewsSourceEntity>

    suspend fun refresh(newsSources: List<NewsSourceEntity>)
}