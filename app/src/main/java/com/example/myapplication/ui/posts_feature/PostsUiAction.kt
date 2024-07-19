package com.example.myapplication.ui.posts_feature

sealed class PostsUiAction(){
    data class NavigateToDetailsWithId(val id: String) : PostsUiAction()
}