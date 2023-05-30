package com.example.vrgsofttest.data

data class RedditPost(
    val author: String,
    val num_comments: Int,
    val thumbnail: String,
    val created_utc: Long,
    val title: String,
    val is_video:Boolean,
)
data class RedditResponse(
    val data: RedditData
)

data class RedditData(
    val children: List<RedditPostWrapper>
)

data class RedditPostWrapper(
    val data: RedditPost
)