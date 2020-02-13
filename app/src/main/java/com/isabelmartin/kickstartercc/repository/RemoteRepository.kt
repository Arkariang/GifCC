package com.isabelmartin.kickstartercc.repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: provide interceptor, client, repository, API via Dagger if you have time
class RemoteRepository {
    private val interceptor = HttpLoggingInterceptor()
    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor.apply { this.level = HttpLoggingInterceptor.Level.BODY })
        .build()

    private val repository: Retrofit = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(GiphyAPI.BASE_URL)
        .client(client)
        .build()

    fun provideAPI(): GiphyAPI = repository.create(GiphyAPI::class.java)
}
