package com.example.myapplication.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.myapplication.domain.model.Post
import com.example.myapplication.ui.posts_feature.components.PostListItem
import com.example.myapplication.ui.posts_feature.components.PostsScreenRoot


@Composable
fun MainNavgraph(
    navController: NavHostController,
){
    NavHost(
        navController = navController,
        startDestination = Screen.PostsList.route
    ) {
        composable(
            route = Screen.PostsList.route
        ) {
            PostsScreenRoot { post ->
                navController.navigate(Screen.PostItem.route + "/${post.id}/${post.title}/${post.userId}")
            }
        }
        composable(
            route = Screen.PostItem.route + "/{postId}/{postTitle}/{postUserId}"
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId") ?: ""
            val postTitle = backStackEntry.arguments?.getString("postTitle") ?: ""
            val postUserIdString = backStackEntry.arguments?.getString("postUserId") ?: "0"
            val postUserId = postUserIdString.toIntOrNull() ?: 0

            val post = Post(id = postId.toInt(), title = postTitle, userId = postUserId)
            PostListItem(post = post){}
        }
    }
}

