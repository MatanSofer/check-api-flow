package com.example.myapplication.data.remote

import com.example.myapplication.data.remote.model.PostDto
import retrofit2.http.GET

interface PostsApi {
    @GET("/posts")
    suspend fun getPosts():List<PostDto>
}