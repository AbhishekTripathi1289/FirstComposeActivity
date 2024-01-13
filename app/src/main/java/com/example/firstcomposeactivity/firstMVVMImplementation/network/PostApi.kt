package com.example.firstcomposeactivity.firstMVVMImplementation.network

import com.example.firstcomposeactivity.firstMVVMImplementation.model.PostItem
import retrofit2.Response
import retrofit2.http.GET

interface PostApi {
    @GET("posts")
    suspend fun fetchPost():Response<List<PostItem>>
}