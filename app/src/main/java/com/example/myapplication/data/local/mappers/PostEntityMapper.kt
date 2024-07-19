package com.example.myapplication.data.local.mappers

import com.example.myapplication.data.local.model.PostEntity
import com.example.myapplication.data.remote.model.PostDto
import com.example.myapplication.domain.model.Post


fun PostEntity.toPost(): Post {
    return Post(
        userId = userId,
        id = id ?: 0,
        title = title
    )
}

fun Post.toPostEntity(): PostEntity {
    return PostEntity(
        userId = userId,
        id = id,
        title = title,
        body = ""
    )
}