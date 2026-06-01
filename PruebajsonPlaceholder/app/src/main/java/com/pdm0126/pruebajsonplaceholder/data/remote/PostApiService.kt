package com.pdm0126.pruebajsonplaceholder.data.remote

import com.pdm0126.pruebajsonplaceholder.data.model.Post
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PostApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @POST("posts")
    suspend fun createPost(@Body post: Post): Post
}