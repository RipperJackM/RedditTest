package com.example.reddittest.network

import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Retrofit

object RedditApi : KoinComponent {

    private val retrofit: Retrofit by inject()

    val retrofitService: RedditApiService by lazy {
        retrofit.create(RedditApiService::class.java)
    }
}