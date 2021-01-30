package com.example.reddittest.network

import com.example.reddittest.network.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface RedditApiService {

    // add subreddit because if use /top only server return empty self_text field
    @GET("r/learnpython/top.json?limit=100")
    suspend fun getAllPostsFromNetwork(): ResponseModel
}