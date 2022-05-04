package com.gurtam.task.data.local.dao

import android.content.Context
import androidx.paging.PagingSource
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.gurtam.task.data.local.NewsLocalDatabase
import com.gurtam.task.data.local.entites.ArticleEntity
import com.gurtam.task.data.util.TestUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ArticleDaoTest {

    private lateinit var database: NewsLocalDatabase
    private lateinit var articleDao: ArticleDao

    private val testArticleEntity = TestUtil.createArticleEntity()
    private val expectedArticlesList = listOf(testArticleEntity)

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, NewsLocalDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        articleDao = database.articleDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    private suspend fun getActualArticles(): List<ArticleEntity> {
        val pagingSourceParams = PagingSource.LoadParams.Refresh(
            loadSize = 10,
            placeholdersEnabled = false,
            key = 1
        )
        val pagingSource = articleDao.articlesBySourceIdPageSource(testArticleEntity.newsSourceId)
            .load(pagingSourceParams) as PagingSource.LoadResult.Page
        return pagingSource.data
    }

    @Test
    fun insertAll_Success() = runTest {
        articleDao.insertAll(expectedArticlesList)
        assertThat(getActualArticles()).isEqualTo(expectedArticlesList)
    }

    @Test
    fun when_clearAllBySourceId_Success() = runTest {
        articleDao.insertAll(expectedArticlesList)
        articleDao.clearAllBySourceId(expectedArticlesList[0].newsSourceId)
        assertThat(getActualArticles()).isEmpty()
    }

    @Test
    fun refresh_Success() = runTest {
        articleDao.refresh(expectedArticlesList, expectedArticlesList[0].newsSourceId)
        assertThat(getActualArticles()).isEqualTo(expectedArticlesList)
    }
}