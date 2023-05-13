package com.example.postsapplication.features.posts.di

import com.example.postsapplication.features.posts.data.remote.service.PostsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@InstallIn(ViewModelComponent::class)
@Module
class PostsAPIModule {

    @Provides
    fun providePostsApi(retrofit: Retrofit) = retrofit.create(PostsService::class.java)
}