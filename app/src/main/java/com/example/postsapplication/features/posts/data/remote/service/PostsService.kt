package com.example.postsapplication.features.posts.data.remote.service

import com.example.postsapplication.core.data.remote.CloudConfig
import com.example.postsapplication.features.posts.data.remote.models.PopularPostsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsService {
    /**
     * get popular movies api use specific token header called basic token
     * not bearer token
     * */
    @GET(CloudConfig.GET_MOVIES_ID)
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): PopularPostsModel
}