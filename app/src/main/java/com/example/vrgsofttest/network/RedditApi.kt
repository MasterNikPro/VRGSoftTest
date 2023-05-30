package com.example.vrgsofttest.network

import com.example.vrgsofttest.data.RedditResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {
    @GET("/top.json")
    suspend fun getTopPosts(@Query("after") after: String? = null): RedditResponse
}