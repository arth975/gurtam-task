package com.gurtam.task.di.modules

import com.gurtam.task.data.repositories.NewsSourceRepositoryImpl
import com.gurtam.task.data.repositories.sources.newssource.NewsSourceLocalDataSource
import com.gurtam.task.data.repositories.sources.newssource.NewsSourceRemoteDataSource
import com.gurtam.task.domain.usecases.GetAllNewsSourcesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideNewsSourceRepository(
        remoteDataSource: NewsSourceRemoteDataSource,
        localDataSource: NewsSourceLocalDataSource
    ) = NewsSourceRepositoryImpl(remoteDataSource, localDataSource)

    @Provides
    fun provideGetAllNewsSourcesUseCase(newsSourceRepository: NewsSourceRepositoryImpl) =
        GetAllNewsSourcesUseCase(newsSourceRepository)
}