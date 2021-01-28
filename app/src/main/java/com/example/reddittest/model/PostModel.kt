package com.example.reddittest.model

data class PostModel(
    var postId: Long = 0L,
    val authorName: String,
    val date: Long,
    val imageUrl: String = "",
    val commentsCount: Long
)