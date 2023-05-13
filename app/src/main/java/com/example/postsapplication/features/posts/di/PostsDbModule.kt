package com.example.postsapplication.features.posts.di

import android.content.Context
import androidx.room.Room
import com.example.postsapplication.core.data.locale.roomdb.dao.PostsModelDao
import com.example.postsapplication.core.data.locale.roomdb.db.PostsModelDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class PostsDbModule {

    @Provides
    fun providePostsDao(postsModelDb: PostsModelDb): PostsModelDao = postsModelDb.postsModelDao()

    @Provides
    @Singleton
    fun providePostsDb(@ApplicationContext context: Context): PostsModelDb = Room.databaseBuilder(
        context, PostsModelDb::class.java, "database-posts-model"
    ).fallbackToDestructiveMigration().build()
}