package com.example.postsapplication.features.posts.di

import com.example.postsapplication.features.posts.data.locale.datasource.PostsDataLocaleDataSource
import com.example.postsapplication.features.posts.data.locale.datasource.PostsDataLocaleDataSourceImp
import com.example.postsapplication.features.posts.data.remote.datasource.PostsDataRemoteDataSource
import com.example.postsapplication.features.posts.data.remote.datasource.PostsDataRemoteDataSourceImp
import com.example.postsapplication.features.posts.data.repository.PostsRepository
import com.example.postsapplication.features.posts.data.repository.PostsRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class PostsModule {
    @Binds
    abstract fun provideMoviesRepository(postsRepository: PostsRepositoryImp): PostsRepository

    @Binds
    abstract fun provideMoviesRemoteDataSource(postsDataRemoteDataSource: PostsDataRemoteDataSourceImp): PostsDataRemoteDataSource

    @Binds
    abstract fun provideMoviesLocaleDataSource(postsDataLocaleDataSource: PostsDataLocaleDataSourceImp): PostsDataLocaleDataSource
}