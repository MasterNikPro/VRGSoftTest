package com.example.vrgsofttest.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vrgsofttest.data.RedditPost
import com.example.vrgsofttest.domain.GetTopPostUseCase
import kotlinx.coroutines.launch

class MainFragmentViewModel(private val getTopPostUseCase: GetTopPostUseCase, private val savedStateHandle: SavedStateHandle): ViewModel() {

    private val _redditPosts = MutableLiveData<List<RedditPost>>()
    val redditPosts: LiveData<List<RedditPost>> get() = _redditPosts
    private val myState = savedStateHandle.getLiveData<List<RedditPost>>("myState")

    fun setState(newState: List<RedditPost>) {
        savedStateHandle.set("myState", newState)
    }

    fun getState(): List<RedditPost>? {
        return myState.value
    }
    fun fetchTopPost(){
        viewModelScope.launch {
            try {
                val posts = getTopPostUseCase.getData()
                _redditPosts.value = posts
            } catch (_: Exception) {

            }
        }
    }
}