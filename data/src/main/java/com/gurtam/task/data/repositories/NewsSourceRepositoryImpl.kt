package com.gurtam.task.data.repositories

import com.gurtam.task.data.mappers.toDomain
import com.gurtam.task.data.mappers.toEntity
import com.gurtam.task.data.repositories.sources.newssource.NewsSourceLocalDataSource
import com.gurtam.task.data.repositories.sources.newssource.NewsSourceRemoteDataSource
import com.gurtam.task.domain.models.NewsSource
import com.gurtam.task.domain.models.ResultOf
import com.gurtam.task.domain.repositories.NewsSourceRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NewsSourceRepositoryImpl(
    private val remoteDataSource: NewsSourceRemoteDataSource,
    private val localDataSource: NewsSourceLocalDataSource,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) : NewsSourceRepository {

    override suspend fun getAll(): Flow<ResultOf<List<NewsSource>>> = flow {
        ResultOf.success(localDataSource.fetchAll()
            .map { it.toDomain() })
            .also { emit(it) }

        try {
            localDataSource.refresh(remoteDataSource.fetchAll().map { it.toEntity() })
            emit(ResultOf.success(localDataSource.fetchAll().map { it.toDomain() }))
        } catch (e: Exception) {
            emit(ResultOf.error(e, e.message))
        }
    }.flowOn(coroutineDispatcher)
        .catch { exception -> emit(ResultOf.error(exception, exception.message)) }
}