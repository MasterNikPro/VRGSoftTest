package com.example.vrgsofttest.domain

import com.example.vrgsofttest.data.RedditPost
import com.example.vrgsofttest.data.redditapi.RedditApi

interface GetTopPostUseCase {
suspend fun getData():List<RedditPost>
}