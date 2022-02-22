package com.fzellner.movielist.data

import android.util.Log
import com.fzellner.movielist.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val baseUrl = BuildConfig.BASE_URL
    private const val apiKey = BuildConfig.API_KEY
    private const val NETWORK_LAYER_TAG = "NetworkLayer"
    private const val APPLICATION_LAYER_TAG = "ApplicationLayer"

    private fun getHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        val networkLogging = HttpLoggingInterceptor { message ->
            Log.d(NETWORK_LAYER_TAG,message)
        }
        val appLogging = HttpLoggingInterceptor { message ->
            Log.d(APPLICATION_LAYER_TAG,message)
        }

        networkLogging.level = HttpLoggingInterceptor.Level.BODY
        appLogging.level = HttpLoggingInterceptor.Level.BODY

        val headerAuthorizationInterceptor = Interceptor { chain ->
            var request = chain.request()
            val originalHttpUrl: HttpUrl = request.url
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build()
            val requestBuilder = request.newBuilder()
                .addHeader("Content-Type", "application/json")
                .url(url)

            request = requestBuilder.build()
            chain.proceed(request)
        }
        client.interceptors().add(headerAuthorizationInterceptor)

        client.interceptors().add(appLogging)
        client.addNetworkInterceptor(networkLogging)

        return client.build()
    }

    fun <T> builder(
        endpoint: Class<T>
    ): T {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(getGsonBuilder()))
            .client(getHttpClient())
            .build()
            .create(endpoint)
    }

    private fun getGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }


}