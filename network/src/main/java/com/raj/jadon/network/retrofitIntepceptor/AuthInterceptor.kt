package com.raj.jadon.network.retrofitIntepceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        run {
            request.header("Content-Type", "application/json")
        }
        return chain.proceed(request.build())
    }
}