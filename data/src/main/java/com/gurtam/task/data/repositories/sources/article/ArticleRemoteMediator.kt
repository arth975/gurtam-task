package com.gurtam.task.data.repositories.sources.article

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.gurtam.task.data.local.entites.ArticleEntity
import com.gurtam.task.data.mappers.toEntity
import okio.IOException
import retrofit2.HttpException

@ExperimentalPagingApi
class ArticleRemoteMediator(
    private val newsSourceId: String,
    private val remoteDataSource: ArticleRemoteDataSource,
    private val localDataSource: ArticleLocalDataSource
) : RemoteMediator<Int, ArticleEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticleEntity>
    ): MediatorResult {
        try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> state.pages.size + 1
            }

            val response = remoteDataSource.fetchArticlesBySourceIdAndPage(newsSourceId, page)
                .map { it.toEntity() }

            if (response.isEmpty())
                return MediatorResult.Success(endOfPaginationReached = true)

            if (loadType == LoadType.REFRESH)
                localDataSource.refreshArticles(response, newsSourceId)
            else
                localDataSource.addArticles(response)

            return MediatorResult.Success(endOfPaginationReached = false)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    override suspend fun initialize(): InitializeAction = InitializeAction.LAUNCH_INITIAL_REFRESH

}