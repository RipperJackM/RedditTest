package com.example.reddittest.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.reddittest.model.PostModel
import com.example.reddittest.network.model.ResponseModel

interface PostRepository {

    @WorkerThread
    fun getAllPosts(): LiveData<List<PostModel>>

    @WorkerThread
    suspend fun insertPosts(list: List<PostModel>)

    @WorkerThread
    fun clearTable()

    @WorkerThread
    suspend fun getPostsFromNetwork(): Boolean
}