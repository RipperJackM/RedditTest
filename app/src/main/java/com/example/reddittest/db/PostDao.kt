package com.example.reddittest.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.reddittest.model.PostModel

@Dao
interface PostDao {

    @Query(value = "SELECT * FROM ${PostEntity.TABLE_NAME}")
    fun getAllPosts(): LiveData<List<PostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<PostEntity>)

    @Query(value = "DELETE FROM ${PostEntity.TABLE_NAME}")
    fun clearTable()
}