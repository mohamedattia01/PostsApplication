package com.example.postsapplication.features.postdetails.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.postsapplication.R
import com.example.postsapplication.core.base.screens.BaseFragment
import com.example.postsapplication.core.constans.AppConstants
import com.example.postsapplication.core.constans.AppConstants.Companion.POST_IMAGE_KEY
import com.example.postsapplication.core.constans.AppConstants.Companion.POST_IMAGE_OVERVIEW
import com.example.postsapplication.core.constans.AppConstants.Companion.POST_IMAGE_TITLE
import com.example.postsapplication.core.custom_views.blur.RenderScriptBlur
import com.example.postsapplication.core.extensions.loadImage
import com.example.postsapplication.databinding.FragmentPostDetailsBinding
import com.example.postsapplication.features.postdetails.presentation.view_model.PostDetailsState
import com.example.postsapplication.features.postdetails.presentation.view_model.PostsDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailsFragment :
    BaseFragment<FragmentPostDetailsBinding, PostDetailsState>(R.layout.fragment_post_details) {
    override val viewModel: PostsDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
    }

    private fun bindViews() {
        val postImage = arguments?.getString(POST_IMAGE_KEY)
        val postTitle = arguments?.getString(POST_IMAGE_TITLE)
        val postOverView = arguments?.getString(POST_IMAGE_OVERVIEW)

        dataBinding.apply {
            postImageView.loadImage(
                AppConstants.IMAGE_URL_SUFFIX.plus(postImage),
            )
            postNameTv.text = postTitle
            postTypeTv.text = postOverView
            postConstraintLayout.let {
                blurView.setupWith(it).setBlurAlgorithm(RenderScriptBlur(requireContext()))
                    ?.setBlurRadius(10f)?.setBlurAutoUpdate(true)?.setBlurEnabled(true)
                    ?.setHasFixedTransformationMatrix(true)
            }
        }
    }
}