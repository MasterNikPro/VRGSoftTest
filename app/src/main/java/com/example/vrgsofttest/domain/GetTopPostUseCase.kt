package com.example.vrgsofttest.domain

import com.example.vrgsofttest.data.RedditPost

interface GetTopPostUseCase {
suspend fun getData():List<RedditPost>
}