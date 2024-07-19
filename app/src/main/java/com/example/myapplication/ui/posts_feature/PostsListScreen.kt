package com.example.myapplication.ui.posts_feature.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.ui.posts_feature.PostsListViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.domain.model.Post
import com.example.myapplication.ui.posts_feature.PostsUiAction
import com.example.myapplication.ui.posts_feature.PostsUiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PostsScreenRoot(
    viewModel: PostsListViewModel = hiltViewModel(),
    onNavigateToDetails:(Post) -> Unit
) {
    PostsScreenList(viewModel,onNavigateToDetails)
}

@Composable
fun PostsScreenList(
    viewModel: PostsListViewModel,
    onNavigateToDetails:(Post) -> Unit
) {
    val state = viewModel.state.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.posts) { post ->
                    PostListItem(
                        post = post,
                        onItemClick = { onNavigateToDetails.invoke(post) }
                    )
                }
            }
            if(state.error.isNotBlank()){
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if(state.isLoading){
//                CircularProgressIndicator(
//                    modifier = Modifier.align(Alignment.Center),
//                )
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is PostsUiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = "Dismiss"
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//private fun  PostListScreenPreview(){
//    PostsScreenList(
//
//    )
//}