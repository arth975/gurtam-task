package com.gurtam.task.data.network.interceptors

import com.gurtam.task.data.network.Routes
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.Route

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", Routes.API_KEY)
            .build()

        return chain.proceed(request)
    }
}