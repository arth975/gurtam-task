package com.gurtam.task.di.modules

import com.gurtam.task.data.network.Routes
import com.gurtam.task.data.network.interceptors.AuthenticationInterceptor
import com.gurtam.task.data.network.services.NewsSourceService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideAuthenticationInterceptor() = AuthenticationInterceptor()

    @Provides
    fun provideOkHttpClient(authenticationInterceptor: AuthenticationInterceptor) = OkHttpClient.Builder()
        .addInterceptor(authenticationInterceptor)
        .build()

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Routes.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideNewsSourceService(retrofit: Retrofit): NewsSourceService =
        retrofit.create(NewsSourceService::class.java)
}