package com.example.postsapplication.features.posts.data.locale.datasource

import com.example.postsapplication.features.posts.data.locale.dbmodels.DbPostsModel

interface PostsDataLocaleDataSource {

    suspend fun insertPostsModel(postsModel: DbPostsModel)

    suspend fun getPostsModelList(): List<DbPostsModel>
}