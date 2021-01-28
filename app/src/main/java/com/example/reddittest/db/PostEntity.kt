package com.example.reddittest.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.reddittest.db.PostEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class PostEntity(
    @PrimaryKey(autoGenerate = true) var postId: Long = 0L,
    val authorName: String,
    val date: Long,
    val imageUrl: String,
    val commentsCount: Long,
) {

    companion object {
        const val TABLE_NAME = "reddit_table"
    }
}