package com.frogsm.instagram_demo.data.util

import com.frogsm.instagram_demo.data.authorize.AuthorizeLocalDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val authorizeLocalDataSource: AuthorizeLocalDataSource
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val newRequest = originRequest.newBuilder()

        val accessToken = runBlocking { authorizeLocalDataSource.getAccessToken() }

        accessToken?.run {
            val newUrl = originRequest.url.newBuilder()
                .addQueryParameter("access_token", accessToken.token)
                .build()

            newRequest.url(newUrl)
        }

        return chain.proceed(newRequest.build())
    }
}