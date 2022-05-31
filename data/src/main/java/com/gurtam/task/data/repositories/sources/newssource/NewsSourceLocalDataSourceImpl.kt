package com.gurtam.task.data.repositories.sources.newssource

import com.gurtam.task.data.local.dao.NewsSourceDao
import com.gurtam.task.data.local.entites.NewsSourceEntity

class NewsSourceLocalDataSourceImpl(private val newsSourceDao: NewsSourceDao) :
    NewsSourceLocalDataSource {

    override suspend fun fetchAll(): List<NewsSourceEntity> = newsSourceDao.getAllNewsSources()

    override suspend fun refresh(newsSources: List<NewsSourceEntity>) {
        newsSourceDao.refreshNewsSources(newsSources)
    }

}