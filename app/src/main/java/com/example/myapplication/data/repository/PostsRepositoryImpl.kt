package com.example.myapplication.data.repository

import com.example.myapplication.data.local.PostDao
import com.example.myapplication.data.local.mappers.toPost
import com.example.myapplication.data.local.mappers.toPostEntity
import com.example.myapplication.data.remote.PostsApi
import com.example.myapplication.data.remote.mappers.toPost
import com.example.myapplication.domain.model.Post
import com.example.myapplication.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PostsRepositoryImpl(
    private val api: PostsApi,
    private val postsDao: PostDao
): PostsRepository {
    override suspend fun getPostsFromApi(): List<Post> {
        return api.getPosts().map {posts ->
            posts.toPost()
        }
    }

    override fun getPostsFromLocal(): Flow<List<Post>> {
        return postsDao.getPosts().map { posts ->
            posts.map { it.toPost() }
        }
    }

    override suspend fun getPostByIdFromLocar(id: Int): Post? {
        return postsDao.getPostById(id)?.toPost()
    }

    override suspend fun insertPostToLocal(post: Post) {
        return postsDao.insertPost(post.toPostEntity())
    }

    override suspend fun deletePostFromLocal(post: Post) {
        return postsDao.deleteNote(post.toPostEntity())
    }

    override suspend fun insertAllPost(posts: List<Post>) {
        postsDao.insertPosts(posts.map { post->
            post.toPostEntity()
        })
    }
}