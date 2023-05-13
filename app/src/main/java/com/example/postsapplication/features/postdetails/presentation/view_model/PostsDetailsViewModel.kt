package com.example.postsapplication.features.postdetails.presentation.view_model

import com.example.postsapplication.core.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostsDetailsViewModel @Inject constructor() :
    BaseViewModel<PostDetailsState>(PostDetailsState.InitialState)