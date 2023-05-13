package com.example.postsapplication.features.posts.data.repository

import com.example.postsapplication.core.data.remote.NetworkResponse
import com.example.postsapplication.features.posts.data.locale.datasource.PostsDataLocaleDataSource
import com.example.postsapplication.features.posts.data.locale.dbmodels.DbPostsModel
import com.example.postsapplication.features.posts.data.remote.datasource.PostsDataRemoteDataSource
import com.example.postsapplication.features.posts.data.remote.models.PopularPostsModel
import javax.inject.Inject

class PostsRepositoryImp @Inject constructor(
    private val postsDataRemoteDataSource: PostsDataRemoteDataSource,
    private val postsDataLocaleDataSource: PostsDataLocaleDataSource
) : PostsRepository {

    override suspend fun getPosts(currentPage: Int): NetworkResponse<PopularPostsModel> =
        postsDataRemoteDataSource.getPopularPosts(currentPage)

    override suspend fun insertPostsModel(postsModel: DbPostsModel) {
        postsDataLocaleDataSource.insertPostsModel(postsModel)
    }

    override suspend fun getPostsModelList(): List<DbPostsModel> =
        postsDataLocaleDataSource.getPostsModelList()

}