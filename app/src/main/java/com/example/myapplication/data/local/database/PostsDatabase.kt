package com.example.myapplication.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.local.PostDao
import com.example.myapplication.data.local.model.PostEntity

@Database(
    entities = [PostEntity::class],
    version = 1
)
abstract class PostsDatabase: RoomDatabase() {
    abstract val dao: PostDao
}