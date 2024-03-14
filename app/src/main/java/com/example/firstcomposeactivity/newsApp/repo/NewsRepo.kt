package com.example.firstcomposeactivity.newsApp.repo

import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.firstcomposeactivity.newsApp.api.NewsApiInterface
import com.example.firstcomposeactivity.newsApp.model.NewsModel
import com.example.firstcomposeactivity.newsApp.util.NewsConstant.API_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import javax.inject.Inject

class NewsRepo @Inject constructor(var newsApiInterface: NewsApiInterface)
{
    suspend fun fetchPostList(filterQuery : String): Flow<DataState<List<NewsModel>>>
    {
        return flow {
            try {
                emit(DataState.Loading)
                var response = newsApiInterface.getNewsList(apiKey = API_KEY, sortBy = "publishedAt", filerQuery = filterQuery, from = "2024-02-12")
                if(response.isSuccessful && response.body() != null)
                {
                    response.body()?.let {
                        emit(DataState.Success(it.articles))
                    }
                }
                else if(response.errorBody() != null)
                {
                    val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                    emit(DataState.Error(message = errorObj.getString("message")))
                }
                else{
                    emit(DataState.Error(message = "Something Went Wrong"))
                }

            }catch (exception: Exception)
            {
                emit(DataState.Error(message = exception.message))
            }
        }
    }
}