package com.example.reddittest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reddittest.model.PostModel
import com.example.reddittest.repository.PostRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class PostListViewModel: ViewModel(), KoinComponent {

    private val repository: PostRepository by inject()

    private val networkResult = MutableLiveData<Boolean>()
    val _networkResult = networkResult

    fun getAllPosts(): LiveData<List<PostModel>> = repository.getAllPosts()

    suspend fun getPostsFromNetwork() {
        networkResult.postValue(repository.getPostsFromNetwork())
    }
}