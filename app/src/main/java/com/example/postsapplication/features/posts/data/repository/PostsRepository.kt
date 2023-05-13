package com.example.postsapplication.features.posts.data.repository

import com.example.postsapplication.core.data.remote.NetworkResponse
import com.example.postsapplication.features.posts.data.locale.dbmodels.DbPostsModel
import com.example.postsapplication.features.posts.data.remote.models.PopularPostsModel

interface PostsRepository {
    suspend fun getPosts(currentPage: Int): NetworkResponse<PopularPostsModel>

    suspend fun insertPostsModel(postsModel: DbPostsModel)

    suspend fun getPostsModelList(): List<DbPostsModel>
}