package com.example.vrgsofttest.network

import com.example.vrgsofttest.data.redditapi.RedditApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.reddit.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val redditApi: RedditApi = retrofit.create(RedditApi::class.java)
}