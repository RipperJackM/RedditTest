package com.example.reddittest.model

data class PostModel(
    var postId: Long = 0L,
    val authorName: String?,
    val title: String?,
    val body: String?,
    val date: Double?,
    val imageUrl: String? = "",
    val commentsCount: Int?
)