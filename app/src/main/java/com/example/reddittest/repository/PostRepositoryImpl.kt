package com.example.reddittest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.reddittest.db.RedditDb
import com.example.reddittest.model.PostMapper
import com.example.reddittest.model.PostModel
import com.example.reddittest.network.RedditApi
import com.example.reddittest.network.model.ResponseModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class PostRepositoryImpl: PostRepository, KoinComponent {

    private val db: RedditDb by inject()
    private val mapper: PostMapper by inject()
    private val postDao = db.postDao()
    private val retrofitService = RedditApi.retrofitService

    override fun getAllPosts(): LiveData<List<PostModel>> = Transformations.map(postDao.getAllPosts()) { mapper.mapToModel(it) }

    override fun insertPosts(list: List<PostModel>) = postDao.insert(mapper.mapToEntity(list))

    override fun clearTable() = postDao.clearTable()

    override suspend fun getPostsFromNetwork(): ResponseModel = retrofitService.getAllPostsFromNetwork()
    
}