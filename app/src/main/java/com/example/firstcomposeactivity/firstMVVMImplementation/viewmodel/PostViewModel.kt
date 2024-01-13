package com.example.firstcomposeactivity.firstMVVMImplementation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithmitch.daggerhiltplayground.util.DataState
import com.example.firstcomposeactivity.firstMVVMImplementation.model.PostItem
import com.example.firstcomposeactivity.firstMVVMImplementation.repo.PostRepositary
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(var postRepositary: PostRepositary): ViewModel()
{
    private val _postMutableState : MutableState<DataState<List<PostItem>>> = mutableStateOf(DataState.Empty)
    val postMutableState : State<DataState<List<PostItem>>>
        get() = _postMutableState


    fun fetchPost()
    {
        viewModelScope.launch(Dispatchers.IO) {
            postRepositary.fetchPostList().onEach {
                _postMutableState.value = it
            }.launchIn(viewModelScope)

        }
    }
}