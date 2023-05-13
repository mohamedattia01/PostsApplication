package com.example.postsapplication.features.postdetails.presentation.view_model

import com.example.postsapplication.core.base.viewmodel.ViewState

sealed class PostDetailsState : ViewState {
    object InitialState : PostDetailsState()
}
