package com.example.postsapplication.features.posts.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.postsapplication.R
import com.example.postsapplication.core.base.screens.BaseFragment
import com.example.postsapplication.core.constans.AppConstants.Companion.POST_IMAGE_KEY
import com.example.postsapplication.core.constans.AppConstants.Companion.POST_IMAGE_OVERVIEW
import com.example.postsapplication.core.constans.AppConstants.Companion.POST_IMAGE_TITLE
import com.example.postsapplication.databinding.FragmentPostsBinding
import com.example.postsapplication.features.posts.data.remote.models.PopularPostsModel
import com.example.postsapplication.features.posts.data.remote.models.Result
import com.example.postsapplication.features.posts.presentation.adapter.PostsAdapter
import com.example.postsapplication.features.posts.presentation.adapter.PostsPagingSource
import com.example.postsapplication.features.posts.presentation.view_model.PostsState
import com.example.postsapplication.features.posts.presentation.view_model.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostsFragment : BaseFragment<FragmentPostsBinding, PostsState>(R.layout.fragment_posts),
    PostsAdapter.OnViewClickListener {

    private val postsAdapter = PostsAdapter(this)
    override val viewModel: PostsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoader()
        viewModel.getPopularPosts(1)
        initPostsRecyclerView()
        observeViewStates()
    }

    private fun observeViewStates() {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.uiState.collect {
                hideLoader()
                when (it) {
                    PostsState.InitialState -> {
                        showLoader()
                    }

                    is PostsState.ErrorData -> {
                        if (PostsPagingSource.currentPage <= it.localPostsModelList.size) {
                            val postsModel = PopularPostsModel(
                                page = it.localPostsModelList[PostsPagingSource.currentPage].page,
                                results = it.localPostsModelList[PostsPagingSource.currentPage].resultList.results
                            )
                            val localPostsList = Pager(PagingConfig(1)) {
                                PostsPagingSource(viewModel, postsModel, true)
                            }.flow.cachedIn(this)
                            localPostsList.collectLatest { pagingData ->
                                postsAdapter.submitData(pagingData)
                            }
                        }
                    }

                    is PostsState.PopularPostsData -> {
                        val remotePostsList = Pager(PagingConfig(1)) {
                            PostsPagingSource(viewModel, it.popularPostsModel, false)
                        }.flow.cachedIn(this)

                        remotePostsList.collectLatest { pagingData ->
                            postsAdapter.submitData(pagingData)
                        }
                    }
                }
            }

        }
    }

    private fun initPostsRecyclerView() {
        dataBinding.postsRecyclerView.adapter = postsAdapter
    }

    private fun showLoader() {
        viewModel.setLoading(true)
    }

    private fun hideLoader() {
        viewModel.setLoading(false)
    }

    override fun onClick(item: Result) {
        val bundle = Bundle()
        bundle.putString(POST_IMAGE_KEY, item.poster_path)
        bundle.putString(POST_IMAGE_TITLE, item.original_title)
        bundle.putString(POST_IMAGE_OVERVIEW, item.overview)
        findNavController().navigate(R.id.post_details_fragment, bundle)
    }
}