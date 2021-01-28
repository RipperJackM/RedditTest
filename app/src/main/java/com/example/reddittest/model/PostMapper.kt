package com.example.reddittest.model

import com.example.reddittest.db.PostEntity
import com.example.reddittest.utils.Mapper

class PostMapper : Mapper<PostEntity, PostModel> {

    override fun mapToEntity(model: PostModel) = with(model) {
        PostEntity(
            this.postId,
            this.authorName,
            this.date,
            this.imageUrl,
            this.commentsCount
        )
    }

    override fun mapToModel(entity: PostEntity) = with(entity) {
        PostModel(
            this.postId,
            this.authorName,
            this.date,
            this.imageUrl,
            this.commentsCount
        )
    }
}