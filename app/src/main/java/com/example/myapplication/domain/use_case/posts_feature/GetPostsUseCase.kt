package com.example.myapplication.domain.use_case.posts_feature

import com.example.myapplication.domain.model.Post
import com.example.myapplication.domain.repository.PostsRepository
import com.example.myapplication.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject


class GetPostsUseCase @Inject constructor(
    private val repository: PostsRepository
){
    operator fun invoke(): Flow<Resource<List<Post>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val allPosts = repository.getPostsFromApi()
                emit(Resource.Success(allPosts))
            }
            catch (e: HttpException){
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured",data = null))
            }
            catch (e: Exception){
                emit(Resource.Error(e.localizedMessage ?: "Couldn't reach server check Internet connection",data = null))
            }
        }
    }

}
