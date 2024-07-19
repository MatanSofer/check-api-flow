package com.example.myapplication.ui.posts_feature.model

import com.example.myapplication.domain.model.Post

data class PostListState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val error: String = ""
)
