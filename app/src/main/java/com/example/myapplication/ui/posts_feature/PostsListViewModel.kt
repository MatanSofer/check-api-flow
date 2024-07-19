package com.example.myapplication.ui.posts_feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.use_case.posts_feature.GetPostsUseCase
import com.example.myapplication.ui.posts_feature.model.PostListState
import com.example.myapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
class PostsListViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(PostListState())
    val state: StateFlow<PostListState> = _state

    private val _eventFlow = MutableSharedFlow<PostsUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getPosts()
    }


    private fun getPosts(){
        viewModelScope.launch(Dispatchers.IO) {
            getPostsUseCase().collect{ result ->
                when(result){
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            posts = result.data ?: emptyList()
                        )
                        withContext(Dispatchers.IO) {
                            _eventFlow.emit(PostsUiEvent.ShowSnackBar("success"))
                        }
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            error = result.message ?: "an error accured"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun onAction(action: PostsUiAction){
        when(action){
            is PostsUiAction.NavigateToDetailsWithId -> {
                //navController.navigateUp()
            }
        }
    }

}