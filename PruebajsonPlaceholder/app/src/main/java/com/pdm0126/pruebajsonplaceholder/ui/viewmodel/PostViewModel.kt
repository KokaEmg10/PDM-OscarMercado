package com.pdm0126.pruebajsonplaceholder.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm0126.pruebajsonplaceholder.data.model.Post
import com.pdm0126.pruebajsonplaceholder.data.remote.RetrofitInstance
import com.pdm0126.pruebajsonplaceholder.data.repository.PostRepositoryImpl
import com.pdm0126.pruebajsonplaceholder.domain.repository.PostRepository
import com.pdm0126.pruebajsonplaceholder.ui.state.UiState
import kotlinx.coroutines.launch

class PostViewModel(
    private val repository: PostRepository = PostRepositoryImpl(RetrofitInstance.api)
) : ViewModel() {

    private val _postsState = mutableStateOf<UiState<List<Post>>>(UiState.Idle)
    val postsState: State<UiState<List<Post>>> = _postsState

    private val _createPostState = mutableStateOf<UiState<Post>>(UiState.Idle)
    val createPostState: State<UiState<Post>> = _createPostState

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            _postsState.value = UiState.Loading
            repository.fetchPosts()
                .onSuccess { _postsState.value = UiState.Success(it) }
                .onFailure {
                    _postsState.value = UiState.Error(it.localizedMessage ?: "Error desconocido")
                }
        }
    }

    fun addPost(title: String, body: String) {
        if (title.isBlank() || body.isBlank()) return

        viewModelScope.launch {
            _createPostState.value = UiState.Loading
            repository.savePost(title, body)
                .onSuccess { newPost ->
                    _createPostState.value = UiState.Success(newPost)
                    val currentState = _postsState.value
                    if (currentState is UiState.Success) {
                        val updatedList = listOf(newPost) + currentState.data
                        _postsState.value = UiState.Success(updatedList)
                    }
                }
                .onFailure {
                    _createPostState.value = UiState.Error(it.localizedMessage ?: "Error al crear")
                }
        }
    }
}