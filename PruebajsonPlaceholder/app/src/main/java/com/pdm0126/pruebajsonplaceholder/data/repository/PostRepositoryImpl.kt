package com.pdm0126.pruebajsonplaceholder.data.repository

import com.pdm0126.pruebajsonplaceholder.data.model.Post
import com.pdm0126.pruebajsonplaceholder.data.remote.PostApiService
import com.pdm0126.pruebajsonplaceholder.domain.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepositoryImpl(private val api: PostApiService) : PostRepository {
    override suspend fun fetchPosts(): Result<List<Post>> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = api.getPosts()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun savePost(title: String, body: String): Result<Post> = withContext(Dispatchers.IO) {
        return@withContext try {
            val newPost = Post(title = title, body = body)
            val response = api.createPost(newPost)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}