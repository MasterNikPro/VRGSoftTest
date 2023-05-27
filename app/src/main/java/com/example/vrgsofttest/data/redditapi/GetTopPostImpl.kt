package com.example.vrgsofttest.data.redditapi

import com.example.vrgsofttest.data.RedditPost
import com.example.vrgsofttest.domain.GetTopPostUseCase
import com.example.vrgsofttest.network.NetworkClient.redditApi

class GetTopPostImpl():GetTopPostUseCase {
    override suspend fun getData(): List<RedditPost> {
        val response = redditApi.getTopPosts()
        return response.data.children.map { it.data }
    }
}