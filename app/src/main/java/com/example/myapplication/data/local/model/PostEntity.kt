package com.example.myapplication.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostEntity(
    val body: String,
    @PrimaryKey val id: Int? = null,
    val title: String,
    val userId: Int
)
