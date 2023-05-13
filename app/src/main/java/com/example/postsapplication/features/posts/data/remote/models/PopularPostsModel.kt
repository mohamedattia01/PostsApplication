package com.example.postsapplication.features.posts.data.remote.models

data class PopularPostsModel(
    val id: Int = 0,
    val page: Int = 1,
    val results: List<Result>,
    val total_pages: Int = 10,
    val total_results: Int = 10
)