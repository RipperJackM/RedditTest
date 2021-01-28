package com.example.reddittest.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PostEntity::class],
    version = RedditDb.DB_VERSION,
    exportSchema = false
)
abstract class RedditDb : RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "reddit_db"
    }
}