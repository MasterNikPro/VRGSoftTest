package com.example.vrgsofttest.data

data class RedditPost(
    val author: String,
    val created: Long,
    val thumbnail: String?,
    val numComments: Int,
    val imageUrl: String?
){

}
