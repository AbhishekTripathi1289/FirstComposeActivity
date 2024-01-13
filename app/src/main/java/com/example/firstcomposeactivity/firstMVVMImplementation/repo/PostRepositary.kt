package com.example.firstcomposeactivity.firstMVVMImplementation.repo

import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.firstcomposeactivity.firstMVVMImplementation.model.PostItem
import com.example.firstcomposeactivity.firstMVVMImplementation.network.PostApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import javax.inject.Inject

class PostRepositary @Inject constructor(var postApi: PostApi)
{
    suspend fun fetchPostList(): Flow<DataState<List<PostItem>>>
    {

        return flow {
            try {
                emit(DataState.Loading)
                var response = postApi.fetchPost()
                if(response.isSuccessful && response.body() != null)
                {
                    response.body()?.let {
                        emit(DataState.Success(it))
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