package com.example.vrgsofttest.data.redditapi

import com.example.vrgsofttest.data.RedditResponse
import retrofit2.http.GET

interface RedditApi {
    @GET("/top.json")
    suspend fun getTopPosts(): RedditResponse
}