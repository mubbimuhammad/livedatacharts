package com.example.graphstest.remote

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://54.88.69.223:8084"
private const val token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtb2JpbGVFbXBAZ21haWwuY29tIiwiaWF0IjoxNzA5ODEzMDg2LCJleHAiOjE3MDk5OTMwODZ9.Pa7bCVVE_ZyP6OLMmyt2VpqcAqy7jAEgLaJoYrUbyslD0fCiTVw_CRf2JRPnlPiJR5Z62j24hBjy6d7-2Z4kvg"

// Interceptor to add Authorization header with the token and logging interceptor
val okHttpClient = OkHttpClient.Builder()
    .addInterceptor { chain ->
        val request: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        chain.proceed(request)
    }
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Change level as needed
    })
    .build()

// Retrofit instance with OkHttpClient and GsonConverterFactory for JSON parsing
val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
