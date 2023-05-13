package com.example.postsapplication.core.di.remote_network

import com.example.postsapplication.core.constans.AppConstants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // Create a Custom Interceptor to add API_KEY to each request
        val request = chain.request().newBuilder()
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")

        request.addHeader("Authorization", "Bearer ${AppConstants.API_KEY}")
        return chain.proceed(request.build())
    }
}