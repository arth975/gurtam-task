package com.gurtam.task.di.modules

import android.content.Context
import com.gurtam.task.data.local.NewsLocalDatabase
import com.gurtam.task.data.local.dao.ArticleDao
import com.gurtam.task.data.local.dao.NewsSourceDao
import com.gurtam.task.data.network.services.ArticleService
import com.gurtam.task.data.network.services.NewsSourceService
import com.gurtam.task.data.repositories.sources.article.*
import com.gurtam.task.data.repositories.sources.newssource.NewsSourceLocalDataSource
import com.gurtam.task.data.repositories.sources.newssource.NewsSourceLocalDataSourceImpl
import com.gurtam.task.data.repositories.sources.newssource.NewsSourceRemoteDataSource
import com.gurtam.task.data.repositories.sources.newssource.NewsSourceRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideNewsLocalDatabase(@ApplicationContext context: Context) =
        NewsLocalDatabase.getInstance(context)

    @Provides
    fun provideNewsSourceDao(db: NewsLocalDatabase) = db.newsSourceDao()

    @Provides
    fun provideArticleDao(db: NewsLocalDatabase) = db.articleDao()

    @Provides
    fun provideNewsSourceRemoteDataSource(newsSourceService: NewsSourceService): NewsSourceRemoteDataSource =
        NewsSourceRemoteDataSourceImpl(newsSourceService)

    @Provides
    fun provideNewsSourceLocalDataSource(newsSourceDao: NewsSourceDao): NewsSourceLocalDataSource =
        NewsSourceLocalDataSourceImpl(newsSourceDao)

    @Provides
    fun provideArticleRemoteDataSource(articleService: ArticleService): ArticleRemoteDataSource =
        ArticleRemoteDataSourceImpl(articleService)

    @Provides
    fun provideArticleLocalDataSource(articleDao: ArticleDao): ArticleLocalDataSource =
        ArticleLocalDataSourceImpl(articleDao)

}