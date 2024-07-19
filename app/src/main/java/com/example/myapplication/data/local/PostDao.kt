package com.example.myapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.local.model.PostEntity
import com.example.myapplication.domain.model.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT * FROM postentity")
    fun getPosts(): Flow<List<PostEntity>>

    @Query("SELECT * FROM postentity WHERE id = :id")
    suspend fun getPostById(id: Int):PostEntity?
    @Delete
    suspend fun deleteNote(post: PostEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: PostEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostEntity>)
}