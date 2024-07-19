package com.example.myapplication.di

import android.app.Application
import androidx.room.Room
import com.example.myapplication.data.local.database.PostsDatabase
import com.example.myapplication.data.remote.PostsApi
import com.example.myapplication.data.repository.PostsRepositoryImpl
import com.example.myapplication.domain.repository.PostsRepository
import com.example.myapplication.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePostsApi(): PostsApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostsApi :: class.java)
    }
    @Singleton
    @Provides
    fun providePostsDatabase(app: Application): PostsDatabase{
        return Room.databaseBuilder(
            app,
            PostsDatabase::class.java,
            "posts.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePostsRepository(api: PostsApi,db: PostsDatabase): PostsRepository{
        return PostsRepositoryImpl(api,db.dao)
    }
}

