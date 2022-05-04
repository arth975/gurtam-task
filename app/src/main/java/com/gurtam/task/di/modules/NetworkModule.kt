package com.gurtam.task.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.gurtam.task.data.network.Routes
import com.gurtam.task.data.network.interceptors.AuthenticationInterceptor
import com.gurtam.task.data.network.serializers.LocalDateTimeDeserializer
import com.gurtam.task.data.network.services.ArticleService
import com.gurtam.task.data.network.services.NewsSourceService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideAuthenticationInterceptor() = AuthenticationInterceptor()

    @Provides
    fun provideOkHttpClient(authenticationInterceptor: AuthenticationInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(authenticationInterceptor)
            .build()

    @Provides
    fun provideLocalDateTimeDeserializer() = LocalDateTimeDeserializer()

    @Provides
    fun provideGsonConverter(deserializer: LocalDateTimeDeserializer): Gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, deserializer)
        .create()

    @Provides
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(Routes.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideNewsSourceService(retrofit: Retrofit): NewsSourceService =
        retrofit.create(NewsSourceService::class.java)

    @Provides
    fun provideArticleService(retrofit: Retrofit): ArticleService =
        retrofit.create(ArticleService::class.java)
}