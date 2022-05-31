package com.gurtam.task.data.repositories.sources.article

import androidx.paging.PagingSource
import com.gurtam.task.data.local.dao.ArticleDao
import com.gurtam.task.data.local.entites.ArticleEntity

class ArticleLocalDataSourceImpl(
    private val articleDao: ArticleDao
) : ArticleLocalDataSource {
    override suspend fun addArticles(articles: List<ArticleEntity>) {
        articleDao.insertAll(articles)
    }

    override suspend fun refreshArticles(articles: List<ArticleEntity>, newsSourceId: String) {
        articleDao.refresh(articles, newsSourceId)
    }

    override fun articlesPagingSource(newsSourceId: String): PagingSource<Int, ArticleEntity> =
        articleDao.articlesBySourceIdPageSource(newsSourceId)
}