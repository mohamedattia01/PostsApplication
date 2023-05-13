package com.example.postsapplication.features.posts.data.locale.datasource

import com.example.postsapplication.core.data.locale.roomdb.db.PostsModelDb
import com.example.postsapplication.features.posts.data.locale.dbmodels.DbPostsModel
import javax.inject.Inject

class PostsDataLocaleDataSourceImp @Inject constructor(private val postsModelDb: PostsModelDb) :
    PostsDataLocaleDataSource {

    override suspend fun insertPostsModel(postsModel: DbPostsModel) {
        postsModelDb.postsModelDao().insertResult(postsModel)
    }

    override suspend fun getPostsModelList(): List<DbPostsModel> =
        postsModelDb.postsModelDao().getPostsModelList()

}