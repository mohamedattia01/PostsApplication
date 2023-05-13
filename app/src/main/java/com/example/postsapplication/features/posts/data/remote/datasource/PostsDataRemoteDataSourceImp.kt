package com.example.postsapplication.features.posts.data.remote.datasource

import com.example.postsapplication.core.constans.AppConstants
import com.example.postsapplication.core.data.remote.NetworkResponse
import com.example.postsapplication.features.posts.data.remote.models.PopularPostsModel
import com.example.postsapplication.features.posts.data.remote.service.PostsService
import javax.inject.Inject

class PostsDataRemoteDataSourceImp @Inject constructor(
    private val postsService: PostsService
) : PostsDataRemoteDataSource {

    override suspend fun getPopularPosts(currentPage: Int): NetworkResponse<PopularPostsModel> {
        return safeApiCall { postsService.getPopularMovies(AppConstants.API_KEY, page = currentPage) }
    }

}