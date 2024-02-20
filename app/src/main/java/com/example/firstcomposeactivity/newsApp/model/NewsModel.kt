package com.example.firstcomposeactivity.newsApp.model

data class NewsModel(var title: String, var description: String , var urlToImage: String)


data class NewsModelResponse(var articles: List<NewsModel>)