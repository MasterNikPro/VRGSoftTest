package com.example.vrgsofttest.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vrgsofttest.data.RedditPost
import com.example.vrgsofttest.domain.GetTopPostUseCase
import kotlinx.coroutines.launch

class MainFragmentViewModel(private val getTopPostUseCase: GetTopPostUseCase): ViewModel() {

    private val _redditPosts = MutableLiveData<List<RedditPost>>()
    val redditPosts: LiveData<List<RedditPost>> get() = _redditPosts

    fun fetchTopPost(){
        viewModelScope.launch {
            try {
                val posts = getTopPostUseCase.getData()
                _redditPosts.value = posts
            } catch (e: Exception) {
                // Handle the exception
            }
        }
    }
}