package com.example.vrgsofttest.domain

import com.example.vrgsofttest.data.RedditPost

interface GetTopPostUseCase {
fun getData():List<RedditPost>
}