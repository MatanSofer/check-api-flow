package com.example.myapplication.data.remote.mappers

import com.example.myapplication.data.remote.model.PostDto
import com.example.myapplication.domain.model.Post

fun PostDto.toPost(): Post {
    return Post(
        userId = userId,
        id = id,
        title = title
    )
}

fun Post.toPostDto(): PostDto {
    return PostDto(
        userId = userId,
        id = id,
        title = title,
        body = ""
    )
}