package com.example.reddittest.network

import com.example.reddittest.network.model.ResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface RedditApiService {

    /** add subreddit because if use /top only - server return empty self_text field
    get only first 100 posts, cause it max value for one get request.
    I think that i can use json field "before" for check is it a last post or no
    and start next request from it position
    but i don't find info in dev/api how use it parameter in request, so we have only 100 first posts :D
     */
    @GET("r/learnpython/top.json?limit=100")
    suspend fun getAllPostsFromNetwork(): ResponseModel
}