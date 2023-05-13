package com.example.postsapplication.features.posts.presentation.view_model

import com.example.postsapplication.core.base.viewmodel.ViewState
import com.example.postsapplication.features.posts.data.locale.dbmodels.DbPostsModel
import com.example.postsapplication.features.posts.data.remote.models.PopularPostsModel

sealed class PostsState : ViewState {
    object InitialState : PostsState()

    data class PopularPostsData(val popularPostsModel: PopularPostsModel) : PostsState()

    data class ErrorData(val localPostsModelList: List<DbPostsModel>, val errorReason: String?) :
        PostsState()
}
