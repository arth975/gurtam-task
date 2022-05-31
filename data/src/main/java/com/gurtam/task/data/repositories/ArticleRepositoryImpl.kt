package com.gurtam.task.data.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import com.gurtam.task.data.mappers.toDomain
import com.gurtam.task.data.repositories.sources.article.ArticleLocalDataSource
import com.gurtam.task.data.repositories.sources.article.ArticleRemoteDataSource
import com.gurtam.task.data.repositories.sources.article.ArticleRemoteMediator
import com.gurtam.task.domain.repositories.ArticleRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

@ExperimentalPagingApi
class ArticleRepositoryImpl(
    private val localDataSource: ArticleLocalDataSource,
    private val remoteDataSource: ArticleRemoteDataSource,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ArticleRepository {
    override suspend fun getArticlesBySourceId(sourceId: String): Flow<*> =
        withContext(coroutineDispatcher) {
            Pager(
                config = PagingConfig(pageSize = 20, initialLoadSize = 20),
                remoteMediator = ArticleRemoteMediator(sourceId, remoteDataSource, localDataSource)
            ) { localDataSource.articlesPagingSource(sourceId) }.flow
                .map { data -> data.map { entity -> entity.toDomain() } }
        }
}