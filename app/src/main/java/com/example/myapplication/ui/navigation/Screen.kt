package com.example.myapplication.ui.navigation

/*
    Used to define all screen routes in application
 */
sealed class Screen(val route: String) {
    object PostsList: Screen("posts_lists_screen")
    object PostItem: Screen("posts_detail_item")
}