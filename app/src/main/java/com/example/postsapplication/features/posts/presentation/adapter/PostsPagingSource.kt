package com.example.postsapplication.features.posts.presentation.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.postsapplication.features.posts.data.remote.models.PopularPostsModel
import com.example.postsapplication.features.posts.data.remote.models.Result
import com.example.postsapplication.features.posts.presentation.view_model.PostsViewModel

class PostsPagingSource(
    private val postsViewModel: PostsViewModel,
    private val currentData: PopularPostsModel,
    private val isFromLocale: Boolean
) : PagingSource<Int, Result>() {

    companion object {
        var currentPage = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            currentPage = params.key ?: 1
            postsViewModel.getPopularPosts(currentPage + 1)

            val nextPage = if (isFromLocale) {
                if (currentPage < currentData.page) currentPage.plus(
                    1
                ) else null
            } else {
                currentPage.plus(
                    1
                )
            }

            LoadResult.Page(
                data = currentData.results,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = nextPage
            )


        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}