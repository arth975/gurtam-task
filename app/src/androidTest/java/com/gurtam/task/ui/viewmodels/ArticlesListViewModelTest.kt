package com.gurtam.task.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.gurtam.task.domain.models.Article
import com.gurtam.task.domain.usecases.GetPagedArticlesBySourceIdUseCase
import com.gurtam.task.models.ArticleUI
import com.gurtam.task.models.mappers.toUI
import com.gurtam.task.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.time.LocalDateTime

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ArticlesListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mGetArticlesUseCase: GetPagedArticlesBySourceIdUseCase
    private lateinit var mViewModel: ArticlesListViewModel

    private val mockNewsSourceID = "news-source-id"
    private val mockArticle = Article("T1", "D1", LocalDateTime.now(), "IURL1", "URL1")
    private val mockArticleUI = mockArticle.toUI()

    @Before
    fun setup() {
        mGetArticlesUseCase = mock(GetPagedArticlesBySourceIdUseCase::class.java)
        mViewModel = ArticlesListViewModel(mGetArticlesUseCase)
    }

    @Test
    fun when_fetchArticles_Success_thenEmit_Resource_Success() = runBlocking {
        val expectedResult = PagingData.from(listOf(mockArticleUI))
        val mockPagingData = PagingData.from(listOf(mockArticle))
        val mockPagingDataFlow = flow { emit(mockPagingData) }

        Mockito.`when`(mGetArticlesUseCase.invoke(mockNewsSourceID)).thenReturn(mockPagingDataFlow)
        mViewModel.fetchArticles(mockNewsSourceID)

        var mockObserver: Observer<Resource<PagingData<ArticleUI>>>? = null
        mockObserver = Observer<Resource<PagingData<ArticleUI>>> { result ->
            val resultData = (result as? Resource.Success)?.data
            assertThat(result).isInstanceOf(Resource.Success::class.java)
            assertThat(resultData).isInstanceOf(expectedResult::class.java)
            assertThat(resultData).isEqualTo(expectedResult)
            mViewModel.articlePagingDataLiveData.removeObserver(mockObserver!!)
        }
        mViewModel.articlePagingDataLiveData.observeForever(mockObserver!!)
    }

    @Test(expected = Exception::class)
    fun when_fetchArticles_Failed_thenEmit_Resource_Error() = runBlocking {
        Mockito.`when`(mGetArticlesUseCase.invoke(mockNewsSourceID)).thenThrow(Exception())
        mViewModel.fetchArticles(mockNewsSourceID)

        var mockObserver: Observer<Resource<PagingData<ArticleUI>>>? = null
        mockObserver = Observer<Resource<PagingData<ArticleUI>>> { result ->
            assertThat(result).isInstanceOf(Resource.Error::class.java)
            mViewModel.articlePagingDataLiveData.removeObserver(mockObserver!!)
        }
        mViewModel.articlePagingDataLiveData.observeForever(mockObserver!!)
    }
}