package com.frogsm.instagram_demo.data.util

import com.frogsm.instagram_demo.BuildConfig
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.reflect.KClass

class ServiceCreator @Inject constructor() {

    fun <T : Any> createService(
        serviceClass: KClass<T>,
        baseUrl: String,
        moshi: Moshi
    ): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(clientBuilder().build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(serviceClass.java)
    }

    private fun clientBuilder(): OkHttpClient.Builder {
        val logInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .apply {
                readTimeout(60, TimeUnit.SECONDS)
                writeTimeout(60, TimeUnit.SECONDS)
                connectTimeout(60, TimeUnit.SECONDS)
            }
    }

}