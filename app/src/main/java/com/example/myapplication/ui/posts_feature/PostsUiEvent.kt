package com.example.myapplication.ui.posts_feature

sealed class PostsUiEvent(){
    data class ShowSnackBar(val message: String): PostsUiEvent()
}
