package com.example.firstcomposeactivity.newsApp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.firstcomposeactivity.firstMVVMImplementation.model.PostItem
import com.example.firstcomposeactivity.newsApp.model.NewsModel
import com.example.firstcomposeactivity.newsApp.repo.NewsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(var newsRepo: NewsRepo) : ViewModel()
{
    private val newLiveData : MutableState<DataState<List<NewsModel>>> = mutableStateOf(DataState.Empty)
    val _newLiveData : State<DataState<List<NewsModel>>>
        get() = newLiveData


    fun fetchPost(filterQuery : String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepo.fetchPostList(filterQuery).onEach {
                newLiveData.value = it
            }.launchIn(viewModelScope)
        }
    }
}