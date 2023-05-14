package com.example.postsapplication.features.posts.presentation.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.postsapplication.MainCoroutineRule
import com.example.postsapplication.features.posts.data.locale.dbmodels.DbPostsModel
import com.example.postsapplication.features.posts.data.locale.dbmodels.ResultList
import com.example.postsapplication.features.posts.data.remote.models.PopularPostsModel
import com.example.postsapplication.features.posts.data.remote.models.Result
import com.example.postsapplication.features.posts.usecase.PostsDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PostsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: PostsViewModel

    @Mock
    private lateinit var postsDataUseCase: PostsDataUseCase

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        MockitoAnnotations.openMocks(this)
        viewModel = PostsViewModel(postsDataUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get posts page 1 when success calling`() {
        runTest {
            Mockito.lenient().`when`(postsDataUseCase.execute(1)).thenReturn(
                PostsState.PopularPostsData(mockedRemotePage1Model())
            )
            viewModel.getPopularPosts(1)
            var actualVal: PostsState.PopularPostsData?
            when (val value = viewModel.uiState.value) {
                is PostsState.PopularPostsData ->
                    actualVal = value

                else -> {}
            }
            actualVal = PostsState.PopularPostsData(mockedRemotePage1Model())
            Assert.assertEquals(
                actualVal, PostsState.PopularPostsData(mockedRemotePage1Model())
            )
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get posts page 2 when success calling`() {
        runTest {
            Mockito.lenient().`when`(postsDataUseCase.execute(2)).thenReturn(
                PostsState.PopularPostsData(mockedRemotePage2Model())
            )
            viewModel.getPopularPosts(2)
            var actualVal: PostsState.PopularPostsData?
            when (val value = viewModel.uiState.value) {
                is PostsState.PopularPostsData ->
                    actualVal = value

                else -> {}
            }
            actualVal = PostsState.PopularPostsData(mockedRemotePage2Model())
            Assert.assertEquals(
                actualVal, PostsState.PopularPostsData(mockedRemotePage2Model())
            )
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get posts page 1 when failure calling`() {
        runTest {
            val postsModel = PopularPostsModel(
                page = mockedLocalePage1Model().page,
                results = mockedLocalePage1Model().resultList.results
            )
            Mockito.lenient().`when`(postsDataUseCase.execute(1)).thenReturn(
                PostsState.ErrorData(listOf(mockedLocalePage1Model()), "undefined http request")
            )
            viewModel.getPopularPosts(2)
            var actualVal: PostsState.ErrorData
            when (val value = viewModel.uiState.value) {
                is PostsState.ErrorData ->
                    actualVal = value

                else -> {}
            }
            actualVal =
                PostsState.ErrorData(listOf(mockedLocalePage1Model()), "undefined http request")
            Assert.assertEquals(
                actualVal,
                PostsState.ErrorData(listOf(mockedLocalePage1Model()), "undefined http request")
            )
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get posts page 2 when failure calling`() {
        runTest {
            val postsModel = PopularPostsModel(
                page = mockedLocalePage2Model().page,
                results = mockedLocalePage2Model().resultList.results
            )
            Mockito.lenient().`when`(postsDataUseCase.execute(2)).thenReturn(
                PostsState.ErrorData(listOf(mockedLocalePage2Model()), "undefined http request")
            )
            viewModel.getPopularPosts(2)
            var actualVal: PostsState.ErrorData
            when (val value = viewModel.uiState.value) {
                is PostsState.ErrorData ->
                    actualVal = value

                else -> {}
            }
            actualVal =
                PostsState.ErrorData(listOf(mockedLocalePage2Model()), "undefined http request")
            Assert.assertEquals(
                actualVal,
                PostsState.ErrorData(listOf(mockedLocalePage2Model()), "undefined http request")
            )
        }
    }


    private fun mockedRemotePage1Model(): PopularPostsModel {
        val result = Result(
            adult = true,
            backdrop_path = "path",
            genre_ids = listOf(1, 2, 3),
            id = 1234,
            original_language = "eng",
            original_title = "title",
            overview = "overview",
            popularity = 1.1,
            poster_path = "path",
            release_date = "date",
            title = "title",
            video = true,
            vote_average = 4.4,
            vote_count = 500
        )
        return PopularPostsModel(
            id = 1, page = 1, results = listOf(result), total_pages = 10, total_results = 10
        )
    }

    private fun mockedRemotePage2Model(): PopularPostsModel {
        val result = Result(
            adult = true,
            backdrop_path = "path",
            genre_ids = listOf(1, 2, 3),
            id = 1234,
            original_language = "eng",
            original_title = "title",
            overview = "overview",
            popularity = 1.1,
            poster_path = "path",
            release_date = "date",
            title = "title",
            video = true,
            vote_average = 4.4,
            vote_count = 500
        )
        return PopularPostsModel(
            id = 1, page = 2, results = listOf(result), total_pages = 10, total_results = 10
        )
    }

    private fun mockedLocalePage1Model(): DbPostsModel {
        val result = Result(
            adult = true,
            backdrop_path = "path",
            genre_ids = listOf(1, 2, 3),
            id = 1234,
            original_language = "eng",
            original_title = "title",
            overview = "overview",
            popularity = 1.1,
            poster_path = "path",
            release_date = "date",
            title = "title",
            video = true,
            vote_average = 4.4,
            vote_count = 500
        )
        return DbPostsModel(
            id = 1, page = 1, ResultList(listOf(result)), total_pages = 10, total_results = 10
        )
    }

    private fun mockedLocalePage2Model(): DbPostsModel {
        val result = Result(
            adult = true,
            backdrop_path = "path",
            genre_ids = listOf(1, 2, 3),
            id = 1234,
            original_language = "eng",
            original_title = "title",
            overview = "overview",
            popularity = 1.1,
            poster_path = "path",
            release_date = "date",
            title = "title",
            video = true,
            vote_average = 4.4,
            vote_count = 500
        )
        return DbPostsModel(
            id = 1, page = 2, ResultList(listOf(result)), total_pages = 10, total_results = 10
        )
    }
}