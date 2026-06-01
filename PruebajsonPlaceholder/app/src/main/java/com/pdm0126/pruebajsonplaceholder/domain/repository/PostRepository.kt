package com.pdm0126.pruebajsonplaceholder.domain.repository

import com.pdm0126.pruebajsonplaceholder.data.model.Post

interface PostRepository {
    suspend fun fetchPosts(): Result<List<Post>>
    suspend fun savePost(title: String, body: String): Result<Post>
}