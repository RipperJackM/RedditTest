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

class PostRepositoryImpl : PostRepository, KoinComponent {

    private val db: RedditDb by inject()
    private val mapper: PostMapper by inject()

    private val postDao = db.postDao()
    private val retrofitService = RedditApi.retrofitService

    override fun getAllPosts(): LiveData<List<PostModel>> = Transformations.map(postDao.getAllPosts()) { mapper.mapToModel(it) }

    override suspend fun insertPosts(list: List<PostModel>) = postDao.insert(mapper.mapToEntity(list))

    override fun clearTable() = postDao.clearTable()

    override suspend fun getPostsFromNetwork(): Boolean {
        val listOfModels = ArrayList<PostModel>()
        val responseModel = retrofitService.getAllPostsFromNetwork()
        val listOfPosts = responseModel.data?.children

        if (listOfPosts != null) {
            for (item in listOfPosts) {
                with(item.data) {
                    listOfModels.add(PostModel(
                            postId = 0L,
                            authorName = this?.author_fullname,
                            title = this?.title,
                            body = this?.selftext,
                            date = this?.created,
                            imageUrl = this?.thumbnail,
                            commentsCount = this?.num_comments))
                }
            }
            insertPosts(listOfModels)
            return true
        }
        return false
    }
}