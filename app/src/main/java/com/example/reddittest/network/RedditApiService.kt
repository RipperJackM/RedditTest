package com.example.reddittest.network

import com.example.reddittest.network.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface RedditApiService {

    @GET("/top.json")
    suspend fun getAllPostsFromNetwork(): ResponseModel
}