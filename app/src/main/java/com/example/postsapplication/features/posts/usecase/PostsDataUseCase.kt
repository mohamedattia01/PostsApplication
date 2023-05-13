package com.example.postsapplication.features.posts.usecase

import com.example.postsapplication.core.base.usecase.SuspendableUseCase
import com.example.postsapplication.core.data.remote.NetworkResponse
import com.example.postsapplication.features.posts.data.locale.dbmodels.DbPostsModel
import com.example.postsapplication.features.posts.data.locale.dbmodels.ResultList
import com.example.postsapplication.features.posts.data.repository.PostsRepository
import com.example.postsapplication.features.posts.presentation.view_model.PostsState
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class PostsDataUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) : SuspendableUseCase<Int, PostsState> {

    override suspend fun execute(input: Int?): PostsState {
        return when (val response = postsRepository.getPosts(input!!)) {
            is NetworkResponse.Success -> {
                // map response object to DbPostsModel
                val localPostsModel = DbPostsModel(
                    response.data.id,
                    response.data.page,
                    ResultList(response.data.results),
                    response.data.total_pages,
                    response.data.total_results
                )
                postsRepository.insertPostsModel(localPostsModel)
                PostsState.PopularPostsData(response.data)
            }

            is NetworkResponse.Failure -> {
                PostsState.ErrorData(
                    postsRepository.getPostsModelList(), response.errorReason
                )
            }
        }
    }
}