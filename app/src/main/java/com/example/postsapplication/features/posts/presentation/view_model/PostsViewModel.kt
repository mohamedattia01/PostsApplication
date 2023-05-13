package com.example.postsapplication.features.posts.presentation.view_model

import androidx.lifecycle.viewModelScope
import com.example.postsapplication.core.base.viewmodel.BaseViewModel
import com.example.postsapplication.features.posts.usecase.PostsDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsDataUseCase: PostsDataUseCase
) : BaseViewModel<PostsState>(PostsState.InitialState) {

    fun getPopularPosts(currentPage: Int) {
        viewModelScope.launch {

            onViewState(postsDataUseCase.execute(currentPage))
        }
    }
}