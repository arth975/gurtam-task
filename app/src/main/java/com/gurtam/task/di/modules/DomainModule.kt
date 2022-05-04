package com.gurtam.task.di.modules

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import com.gurtam.task.data.repositories.ArticleRepositoryImpl
import com.gurtam.task.data.repositories.NewsSourceRepositoryImpl
import com.gurtam.task.data.repositories.sources.article.ArticleLocalDataSource
import com.gurtam.task.data.repositories.sources.article.ArticleRemoteDataSource
import com.gurtam.task.data.repositories.sources.newssource.NewsSourceLocalDataSource
import com.gurtam.task.data.repositories.sources.newssource.NewsSourceRemoteDataSource
import com.gurtam.task.domain.repositories.ArticleRepository
import com.gurtam.task.domain.repositories.NewsSourceRepository
import com.gurtam.task.domain.usecases.GetAllNewsSourcesUseCase
import com.gurtam.task.domain.usecases.GetPagedArticlesBySourceIdUseCase
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
    ): NewsSourceRepository = NewsSourceRepositoryImpl(remoteDataSource, localDataSource)

    @Provides
    fun provideGetAllNewsSourcesUseCase(newsSourceRepository: NewsSourceRepository) =
        GetAllNewsSourcesUseCase(newsSourceRepository)

    @ExperimentalPagingApi
    @Provides
    fun provideArticleRepository(
        remoteDataSource: ArticleRemoteDataSource,
        localDataSource: ArticleLocalDataSource
    ): ArticleRepository = ArticleRepositoryImpl(localDataSource, remoteDataSource)

    @Provides
    fun provideGetPagedArticlesBySourceIdUseCas(articleRepository: ArticleRepository) =
        GetPagedArticlesBySourceIdUseCase(articleRepository)
}