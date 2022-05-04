package com.gurtam.task.data.network.services

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.gurtam.task.data.network.dto.ArticleResponse
import com.gurtam.task.data.network.serializers.LocalDateTimeDeserializer
import com.gurtam.task.data.util.ResourceReader
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime

@RunWith(JUnit4::class)
class ArticleServiceTest {

    private val mockServer = MockWebServer()

    private val gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeDeserializer())
        .create()
    private val service = Retrofit.Builder()
        .baseUrl(mockServer.url("/"))
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(ArticleService::class.java)

    @After
    fun tearDown() {
        mockServer.shutdown()
    }

    @Test
    fun fetchNewsBySourceIdAndPage_Success() {
        runBlocking {
            val mockResponse =
                ResourceReader.readJsonString("articleApi/article_success.json")
            mockServer.enqueue(
                MockResponse()
                    .setResponseCode(200)
                    .setBody(mockResponse)
            )

            val actualBody = service.fetchNewsBySourceIdAndPage(0, "")
            val expectedBody = Gson().fromJson(mockResponse, ArticleResponse::class.java)

            assertThat(actualBody).isEqualTo(expectedBody)
        }
    }
}