package com.example.firstcomposeactivity.newsApp.api

import com.example.firstcomposeactivity.newsApp.model.NewsModel
import com.example.firstcomposeactivity.newsApp.model.NewsModelResponse
import com.plcoding.jetpackcomposepokedex.data.remote.responses.PokemonList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiInterface
{
    @GET("everything")
    suspend fun getNewsList(@Query("apiKey") apiKey: String,
                                @Query("sortBy") sortBy: String,
                            @Query("q") filerQuery: String,
                            @Query("from") from: String): Response<NewsModelResponse>
}