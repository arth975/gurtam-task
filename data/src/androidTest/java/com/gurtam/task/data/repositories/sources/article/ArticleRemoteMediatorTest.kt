package com.gurtam.task.data.repositories.sources.article

import androidx.paging.*
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.gurtam.task.data.local.NewsLocalDatabase
import com.gurtam.task.data.local.entites.ArticleEntity
import com.gurtam.task.data.network.dto.ArticleDto
import com.gurtam.task.data.util.TestUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.io.IOException

@ExperimentalCoroutinesApi
@ExperimentalPagingApi
@RunWith(AndroidJUnit4::class)
class ArticleRemoteMediatorTest {

    private lateinit var mRemoteDataSource: ArticleRemoteDataSourceImpl
    private lateinit var mLocalDataSource: ArticleLocalDataSourceImpl
    private lateinit var mRemoteMediator: ArticleRemoteMediator
    private lateinit var mDatabase: NewsLocalDatabase

    private val mNewsSourceId = ""
    private val mPage = 1
    private val mPagingState = PagingState<Int, ArticleEntity>(listOf(), null, PagingConfig(10), 10)

    @Before
    fun setup() {
        mDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NewsLocalDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        val articleDao = mDatabase.articleDao()
        mLocalDataSource = ArticleLocalDataSourceImpl(articleDao)
        mRemoteDataSource = mock(ArticleRemoteDataSourceImpl::class.java)
        mRemoteMediator = ArticleRemoteMediator(mNewsSourceId, mRemoteDataSource, mLocalDataSource)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        mDatabase.close()
    }

    private suspend fun mockWhenFetchArticlesBySourceIdAndPage(returnArticles: List<ArticleDto>) {
        Mockito.`when`(mRemoteDataSource.fetchArticlesBySourceIdAndPage(mNewsSourceId, mPage))
            .thenReturn(returnArticles)
    }

    private suspend fun mockFailedFetchArticlesBySourceIdAndPage() {
        Mockito.`when`(mRemoteDataSource.fetchArticlesBySourceIdAndPage(mNewsSourceId, mPage))
            .thenThrow(Exception())
    }

    private fun assertMediatorResult(
        actualResult: RemoteMediator.MediatorResult,
        expectedResultInstance: Class<*>,
        endOfPaginationReached: Boolean
    ) {
        assertThat(actualResult).isInstanceOf(expectedResultInstance)
        if (actualResult is RemoteMediator.MediatorResult.Success)
            assertThat((actualResult).endOfPaginationReached).isEqualTo(endOfPaginationReached)
    }

    @Test
    fun whenResponseIsNotEmpty_thenReturn_MediatorResult_Success_endOfPaginationReached_isFalse() =
        runTest {
            mockWhenFetchArticlesBySourceIdAndPage(TestUtil.createArticleDtoList())
            val result = mRemoteMediator.load(LoadType.REFRESH, mPagingState)
            assertMediatorResult(
                actualResult = result,
                expectedResultInstance = RemoteMediator.MediatorResult.Success::class.java,
                endOfPaginationReached = false
            )
        }

    @Test
    fun whenResponseIsEmpty_thenReturn_MediatorResult_Success_endOfPaginationReached_isTrue() =
        runTest {
            mockWhenFetchArticlesBySourceIdAndPage(emptyList())
            val result = mRemoteMediator.load(LoadType.REFRESH, mPagingState)
            assertMediatorResult(
                actualResult = result,
                expectedResultInstance = RemoteMediator.MediatorResult.Success::class.java,
                endOfPaginationReached = true
            )
        }

    @Test
    fun whenLoadType_is_Prepend_thenReturn_MediatorResult_Success_endOfPaginationReached_isTrue() =
        runTest {
            val result = mRemoteMediator.load(LoadType.PREPEND, mPagingState)
            assertMediatorResult(
                actualResult = result,
                expectedResultInstance = RemoteMediator.MediatorResult.Success::class.java,
                endOfPaginationReached = true
            )
        }

    @Test(expected = Exception::class)
    fun whenFailedLoadRemoteData_thenReturn_MediatorResult_Error() = runTest {
        mockFailedFetchArticlesBySourceIdAndPage()
        val result = mRemoteMediator.load(LoadType.PREPEND, mPagingState)
        assertMediatorResult(
            actualResult = result,
            expectedResultInstance = RemoteMediator.MediatorResult.Error::class.java,
            endOfPaginationReached = true
        )
    }
}