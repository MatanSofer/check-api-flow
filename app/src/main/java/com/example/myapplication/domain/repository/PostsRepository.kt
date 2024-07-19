package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostsRepository {

    suspend fun getPostsFromApi(): List<Post>


    fun getPostsFromLocal(): Flow<List<Post>>
    suspend fun getPostByIdFromLocar(id: Int): Post?
    suspend fun insertPostToLocal(post: Post)
    suspend fun deletePostFromLocal(post: Post)
    suspend fun insertAllPost(post: List<Post>)

}