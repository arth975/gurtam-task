package com.gurtam.task.data.repositories.sources.article

import androidx.paging.PagingSource
import com.gurtam.task.data.local.entites.ArticleEntity

interface ArticleLocalDataSource {

    suspend fun addArticles(articles: List<ArticleEntity>)

    suspend fun refreshArticles(articles: List<ArticleEntity>, newsSourceId: String)

    fun articlesPagingSource(newsSourceId: String): PagingSource<Int, ArticleEntity>
}