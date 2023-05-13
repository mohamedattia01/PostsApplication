package com.example.postsapplication.features.posts.data.remote.datasource

import com.example.postsapplication.core.data.remote.NetworkRemoteServiceCall
import com.example.postsapplication.core.data.remote.NetworkResponse
import com.example.postsapplication.features.posts.data.remote.models.PopularPostsModel

interface PostsDataRemoteDataSource : NetworkRemoteServiceCall {
    suspend fun getPopularPosts(currentPage: Int): NetworkResponse<PopularPostsModel>
}