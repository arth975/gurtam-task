package com.gurtam.task.data.repositories.sources.article

import com.google.common.truth.Truth.assertThat
import com.gurtam.task.data.network.dto.ArticleResponse
import com.gurtam.task.data.network.services.ArticleService
import com.gurtam.task.data.util.ResourceReader
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArticleRemoteDataSourceImplTest {

    @Mock
    lateinit var articleService: ArticleService

    private val expectedResponse =
        ResourceReader.readJsonObject(
            "articleApi/article_success.json",
            ArticleResponse::class.java
        )
    private lateinit var remoteDataSource: ArticleRemoteDataSourceImpl

    @Before
    fun setup() {
        remoteDataSource = ArticleRemoteDataSourceImpl(articleService)
    }

    @Test
    fun fetchArticlesBySourceIdAndPage_Success() {
        runBlocking {
            Mockito.`when`(articleService.fetchNewsBySourceIdAndPage(0, ""))
                .thenReturn(expectedResponse)
            val result = remoteDataSource.fetchArticlesBySourceIdAndPage("", 0)
            assertThat(result).isEqualTo(expectedResponse.articles)
        }
    }
}